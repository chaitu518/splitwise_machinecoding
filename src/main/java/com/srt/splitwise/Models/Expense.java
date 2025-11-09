package com.srt.splitwise.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Expense {
    private long id;
    private String name;
    private double amount;
    private long paidBy;
    private List<Long> userShareIds;
}
