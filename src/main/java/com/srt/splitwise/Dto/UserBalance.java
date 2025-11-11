package com.srt.splitwise.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class UserBalance {
    private long userId;
    private double amount;

}
