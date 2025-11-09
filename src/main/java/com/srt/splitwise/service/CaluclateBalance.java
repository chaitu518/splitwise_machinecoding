package com.srt.splitwise.service;

import com.srt.splitwise.Dto.FairShare;
import com.srt.splitwise.Models.Expense;
import com.srt.splitwise.Models.Group;
import com.srt.splitwise.repo.InMemoryExpenseRepo;
import com.srt.splitwise.repo.InMemoryGroupRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CaluclateBalance {
    private InMemoryGroupRepo groupRepo;
    private InMemoryExpenseRepo expenseRepo;
    public CaluclateBalance(InMemoryGroupRepo groupRepo, InMemoryExpenseRepo expenseRepo) {
        this.groupRepo = groupRepo;
        this.expenseRepo = expenseRepo;
    }
    public FairShare createFairShare(long groupId) {
        Group group = groupRepo.getGroup(groupId);
        Map<Long,Double> netBalance = new HashMap<>();
        List<Long> expensesIds = group.getExpensesIds();
        List<Expense> expenses = new ArrayList<Expense>();
        for (Long expenseId : expensesIds) {
            expenses.add(expenseRepo.getExpense(expenseId));
        }
        for (Expense expense : expenses) {
            double amount = expense.getAmount();
            long paidBy = expense.getPaidBy();
            double share = amount/expense.getUserShareIds().size();
            for (Long userId : expense.getUserShareIds()) {
                netBalance.put(userId, netBalance.getOrDefault(userId,0.0)-share);
            }
            netBalance.put(paidBy, netBalance.getOrDefault(paidBy,0.0)+amount);

        }
        FairShare fairShare = new FairShare();
        fairShare.setShares(netBalance);
        return fairShare;
    }
}
