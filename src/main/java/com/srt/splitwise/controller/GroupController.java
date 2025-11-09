package com.srt.splitwise.controller;

import com.srt.splitwise.Models.Group;
import com.srt.splitwise.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
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
}
