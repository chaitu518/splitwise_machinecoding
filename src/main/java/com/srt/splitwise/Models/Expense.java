package com.srt.splitwise.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Expense {
    private long id;
    private long groupId;
    private String name;
    private double amount;
    private Map<Long,Double> paidBy;
    private List<Long> participantsIds;
}
