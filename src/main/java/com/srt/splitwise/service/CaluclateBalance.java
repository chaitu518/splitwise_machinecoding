package com.srt.splitwise.service;

import com.srt.splitwise.Dto.FairShare;
import com.srt.splitwise.Exceptions.GroupRelatedException;
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
    public FairShare createFairShare(long groupId) throws GroupRelatedException {
        Group group = groupRepo.getGroup(groupId);
        if (group == null) {
            throw new GroupRelatedException("Group not found");
        }
        Map<Long,Double> netBalance = new HashMap<>();
        for(Long userId : group.getMembers()) {
            netBalance.put(userId,0.0);
        }
        List<Long> expensesIds = group.getExpensesIds();
        if(expensesIds.isEmpty()) {
            return new FairShare();
        }
        List<Expense> expenses = new ArrayList<Expense>();
        for (Long expenseId : expensesIds) {
            expenses.add(expenseRepo.getExpense(expenseId));
        }
        for (Expense expense : expenses) {
            double amount = expense.getAmount();
            Map<Long,Double> paidBy = expense.getPaidBy();
            double share = amount/expense.getParticipantsIds().size();
            for (Long userId : expense.getParticipantsIds()) {
                netBalance.put(userId, netBalance.getOrDefault(userId,0.0)-share);
            }
            for (Map.Entry<Long,Double> paidUser : paidBy.entrySet()) {
                netBalance.put(paidUser.getKey(), netBalance.getOrDefault(paidUser.getKey(),0.0) + paidUser.getValue());
            }


        }
        FairShare fairShare = new FairShare();
        fairShare.setShares(netBalance);
        return fairShare;
    }
}
