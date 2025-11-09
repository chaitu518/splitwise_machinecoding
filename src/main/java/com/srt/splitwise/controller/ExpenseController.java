package com.srt.splitwise.controller;

import com.srt.splitwise.Exceptions.GroupRelatedException;
import com.srt.splitwise.Models.Expense;
import com.srt.splitwise.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/groups")
public class ExpenseController {
    private ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService=expenseService;
    }

    @PostMapping("/{groupId}/expenses")
    public Expense addExpense(@PathVariable long groupId, @RequestBody Expense expense) throws GroupRelatedException {
        expense.setGroupId(groupId);
        return expenseService.addExpense(expense);

    }
    @GetMapping("/{groupId}/expenses")
    public List<Long> getAllExpenses(@PathVariable long groupId) throws GroupRelatedException {
        return expenseService.getAllExpenses(groupId);
    }
}
