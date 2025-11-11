package com.srt.splitwise.service;

import com.srt.splitwise.Dto.FairShare;
import com.srt.splitwise.Dto.SettleUserBalance;
import com.srt.splitwise.Dto.UserBalance;
import com.srt.splitwise.Exceptions.GroupRelatedException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleBalanceService {
    private CaluclateBalance caluclateBalance;
    public SettleBalanceService(CaluclateBalance caluclateBalance) {
        this.caluclateBalance = caluclateBalance;
    }
    double epsilon = 0.0001;

    public List<SettleUserBalance> settleUserBalance(long groupId) throws GroupRelatedException {
        FairShare fairShare = caluclateBalance.createFairShare(groupId);
        Map<Long,Double> userBalances = fairShare.getShares();
        List<UserBalance> creaditors = new ArrayList<UserBalance>();
        List<UserBalance> debitors = new ArrayList<UserBalance>();
        for (Map.Entry<Long, Double> entry : userBalances.entrySet()) {
            if(Math.abs(entry.getValue()) < epsilon) {continue;}
            if(entry.getValue()>0)creaditors.add(new UserBalance(entry.getKey(),entry.getValue()));
            if(entry.getValue()<0)debitors.add(new UserBalance(entry.getKey(),entry.getValue()));
        }
        Collections.sort(creaditors,(a,b)->Double.compare(b.getAmount(),a.getAmount()));
        Collections.sort(debitors,(a,b)->Double.compare(b.getAmount(),a.getAmount()));
        List<SettleUserBalance> settleUserBalances = new ArrayList<>();
        int i=0;
        int j=0;
        while(i<creaditors.size() && j<debitors.size()){
            UserBalance creditorBalance = creaditors.get(i);
            UserBalance debitorBalance = debitors.get(j);

            double creditorBalanceAmount = creditorBalance.getAmount();
            double debitorBalanceAmount = Math.abs(debitorBalance.getAmount());
            double pay = Math.min(creditorBalanceAmount,debitorBalanceAmount);
            settleUserBalances.add(new SettleUserBalance(debitorBalance.getUserId(),creditorBalance.getUserId(),pay));
            creditorBalance.setAmount(creditorBalance.getAmount()-pay);
            debitorBalance.setAmount(debitorBalance.getAmount()+pay);

            if(creditorBalance.getAmount()<epsilon)i++;
            if(debitorBalance.getAmount()<epsilon)j++;
        }
        return settleUserBalances;

    }
}
