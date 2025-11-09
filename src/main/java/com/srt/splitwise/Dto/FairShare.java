package com.srt.splitwise.Dto;

import com.srt.splitwise.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FairShare {
    Map<Long,Double> shares;
}
