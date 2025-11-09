package com.srt.splitwise.service;

import com.srt.splitwise.Dto.FairShare;
import com.srt.splitwise.Exceptions.GroupRelatedException;
import com.srt.splitwise.Models.Group;

import java.util.List;

public interface GroupService {
    public Group getGroup(long id) throws GroupRelatedException;
    public List<Group> getGroups();
    public Group createGroup(Group group) throws GroupRelatedException;
    public Group updateGroup(long id,Group group) throws GroupRelatedException;
}
