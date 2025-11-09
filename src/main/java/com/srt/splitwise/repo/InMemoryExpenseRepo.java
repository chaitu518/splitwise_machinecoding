package com.srt.splitwise.repo;

import com.srt.splitwise.Models.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryExpenseRepo {
    Map<Long, Expense> expenses;
    private long id;
    public InMemoryExpenseRepo() {
        expenses = new HashMap<>();
        id=0;
    }
    public Expense add(Expense expense) {
        expense.setId(++id);
        expenses.put(expense.getId(), expense);
        return expense;
    }
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses.values());
    }
    public Expense getExpense(long id) {
        return expenses.get(id);
    }
}
