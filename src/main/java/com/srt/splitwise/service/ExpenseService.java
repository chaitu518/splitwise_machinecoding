package com.srt.splitwise.service;

import com.srt.splitwise.Exceptions.GroupRelatedException;
import com.srt.splitwise.Models.Expense;
import org.springframework.stereotype.Service;

import java.net.InterfaceAddress;
import java.util.List;


public interface ExpenseService {
    public Expense addExpense(Expense expense) throws GroupRelatedException;
    public List<Long> getAllExpenses(long groupid) throws GroupRelatedException;
}
