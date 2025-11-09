package com.srt.splitwise.controller;

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
    public Expense addExpense(@PathVariable long groupId, @RequestBody Expense expense) {
        //System.out.println(groupId+" ");
        expense.setGroupId(groupId);
        return expenseService.addExpense(expense);

    }
    @GetMapping("/{groupId}/expenses")
    public List<Expense> getAllExpenses(@PathVariable long groupId) {
        return expenseService.getAllExpenses();
    }
}
