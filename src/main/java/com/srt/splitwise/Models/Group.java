package com.srt.splitwise.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Group {
    private long id;
    private String name;
    private List<Long> expensesIds;
    private List<Long> members;
}
