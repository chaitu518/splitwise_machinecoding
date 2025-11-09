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
    }
    public Group getGroup(long id) {
        return groups.get(id);
    }
    public Group addGroup(Group group) {
        group.setId(++id);
        groups.put(group.getId(), group);
        return group;
    }
    public List<Group> getAllGroups() {
        return new ArrayList<>(groups.values());
    }
}
