package com.srt.splitwise.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SettleUserBalance {
    long fromUserId;
    long toUserId;
    double paidAmount;


}
