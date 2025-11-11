package com.srt.splitwise.controller;

import com.srt.splitwise.Dto.FairShare;
import com.srt.splitwise.Dto.SettleUserBalance;
import com.srt.splitwise.Exceptions.GroupRelatedException;
import com.srt.splitwise.Models.Group;
import com.srt.splitwise.service.CaluclateBalance;
import com.srt.splitwise.service.GroupService;
import com.srt.splitwise.service.SettleBalanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;
    private CaluclateBalance caluclateBalance;
    private SettleBalanceService settleBalanceService;
    public GroupController(GroupService groupService,CaluclateBalance caluclateBalance,SettleBalanceService settleBalanceService) {
        this.groupService = groupService;
        this.caluclateBalance = caluclateBalance;
        this.settleBalanceService = settleBalanceService;
    }
    @GetMapping("")
    public List<Group> getGroups() {
        return groupService.getGroups();
    }
    @GetMapping("/{id}")
    public Group getGroup(@PathVariable int id) throws GroupRelatedException {
        return groupService.getGroup(id);
    }
    @PostMapping
    public Group addGroup(@RequestBody Group group) throws GroupRelatedException {
        return groupService.createGroup(group);
    }
    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable int id, @RequestBody Group group) throws GroupRelatedException {
        return groupService.updateGroup(id,group);
    }
    @GetMapping("/{id}/Balances")
    public FairShare getGroupBalances(@PathVariable int id) throws GroupRelatedException {
        return caluclateBalance.createFairShare(id);
    }
    @GetMapping("/{id}/SettleBalances")
    public List<SettleUserBalance> getGroupSettleBalances(@PathVariable int id) throws GroupRelatedException {
        return settleBalanceService.settleUserBalance(id);
    }
}
