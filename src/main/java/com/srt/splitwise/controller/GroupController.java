package com.srt.splitwise.controller;

import com.srt.splitwise.Dto.FairShare;
import com.srt.splitwise.Models.Group;
import com.srt.splitwise.service.CaluclateBalance;
import com.srt.splitwise.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;
    private CaluclateBalance caluclateBalance;
    public GroupController(GroupService groupService,CaluclateBalance caluclateBalance) {
        this.groupService = groupService;
        this.caluclateBalance = caluclateBalance;
    }
    @GetMapping("")
    public List<Group> getGroups() {
        return groupService.getGroups();
    }
    @GetMapping("/{id}")
    public Group getGroup(@PathVariable int id) {
        return groupService.getGroup(id);
    }
    @PostMapping
    public Group addGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }
    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable int id, @RequestBody Group group) {
        return groupService.updateGroup(id,group);
    }
    @GetMapping("/{id}/Balances")
    public FairShare getGroupBalances(@PathVariable int id) {
        return caluclateBalance.createFairShare(id);
    }
}
