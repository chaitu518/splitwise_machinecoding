package com.srt.splitwise.repo;

import com.srt.splitwise.Models.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryGroupRepo {
    Map<Long, Group> groups;
    private long id;
    public InMemoryGroupRepo() {
        groups = new HashMap<>();
        id=0;
    }
    public Group getGroup(long groupId) {
        return groups.get(groupId);
    }
    public Group addGroup(Group group) {
        group.setId(++id);
        groups.put(group.getId(), group);
        return group;
    }
    public Group updateGroup(long gid,Group group) {
        Group oldGroup = groups.get(gid);
        if(group.getName()!=null)oldGroup.setName(group.getName());
        if(group.getExpensesIds()!=null)oldGroup.setExpensesIds(group.getExpensesIds());
        groups.put(oldGroup.getId(), oldGroup);
        return group;
    }
    public List<Group> getAllGroups() {
        return new ArrayList<>(groups.values());
    }
}
