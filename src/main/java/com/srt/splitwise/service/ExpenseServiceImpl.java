package com.srt.splitwise.service;

import com.srt.splitwise.Models.Expense;
import com.srt.splitwise.repo.InMemoryExpenseRepo;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private InMemoryExpenseRepo expenseRepo;
    public ExpenseServiceImpl(InMemoryExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }
    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepo.add(expense);
    }
}
