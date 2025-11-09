package com.srt.splitwise.service;


import com.srt.splitwise.Exceptions.GroupRelatedException;
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
    public Group getGroup(long id) throws GroupRelatedException {
        Group group = groupRepo.getGroup(id);
        if (group == null) {
            throw new GroupRelatedException("Group not found");
        }
        return group;
    }

    @Override
    public List<Group> getGroups() {
        return groupRepo.getAllGroups();
    }

    @Override
    public Group createGroup(Group group) throws GroupRelatedException {
        if(groupRepo.getGroup(group.getId()) != null) {
            Group group1 = groupRepo.getGroup(group.getId());
            if (group1 != null) {
                throw new GroupRelatedException("Group with same id is already existed");
            }
        }
        return groupRepo.addGroup(group);
    }

    @Override
    public Group updateGroup(long id,Group group) throws GroupRelatedException {
        Group group1 = groupRepo.getGroup(id);
        if (group1 == null) {
            throw new GroupRelatedException("Group with id:"+id+"not found");
        }
        return groupRepo.updateGroup(id,group);
    }

}
