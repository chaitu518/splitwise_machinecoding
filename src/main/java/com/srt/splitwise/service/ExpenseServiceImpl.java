package com.srt.splitwise.service;

import com.srt.splitwise.Models.Expense;
import com.srt.splitwise.Models.Group;
import com.srt.splitwise.repo.InMemoryExpenseRepo;
import com.srt.splitwise.repo.InMemoryGroupRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private InMemoryExpenseRepo expenseRepo;
    private InMemoryGroupRepo groupRepo;
    public ExpenseServiceImpl(InMemoryExpenseRepo expenseRepo,InMemoryGroupRepo groupRepo) {
        this.expenseRepo = expenseRepo;
        this.groupRepo = groupRepo;
    }
    @Override
    public Expense addExpense(Expense expense) {
        Group group = groupRepo.getGroup(expense.getGroupId());
        if(group == null) {
            throw new RuntimeException("Group not found");
        }
        Expense expense1 = expenseRepo.add(expense);
        List<Long> groupExpenses = group.getExpensesIds();
        if(groupExpenses == null) {
            groupExpenses = new ArrayList<Long>();
        }
        groupExpenses.add(expense1.getId());
        group.setExpensesIds(groupExpenses);
        groupRepo.updateGroup(group.getId(), group);
        return expense1;
    }


    public List<Expense> getAllExpenses() {
        return expenseRepo.getAllExpenses();
    }


}
