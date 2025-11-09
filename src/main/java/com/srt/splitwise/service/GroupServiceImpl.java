package com.srt.splitwise.service;

import com.srt.splitwise.Models.Group;
import com.srt.splitwise.repo.InMemoryGroupRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private InMemoryGroupRepo groupRepo;
    public GroupServiceImpl(InMemoryGroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @Override
    public Group getGroup(long id) {
        return groupRepo.getGroup(id);
    }

    @Override
    public List<Group> getGroups() {
        return groupRepo.getAllGroups();
    }

    @Override
    public Group createGroup(Group group) {
        return groupRepo.addGroup(group);
    }
}
