package com.srt.splitwise.repo;

import com.srt.splitwise.Models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryUserRepo {
    Map<Long, User> users;
    private long id;
    public InMemoryUserRepo() {
        users = new HashMap<>();
        id=0;
    }
    public User getUser(long id) {
        //handle user not found Exception
        return users.get(id);
    }
    public User addUser(User user) {
        user.setId(++id);
        users.put(user.getId(), user);
        return user;
    }
    public void removeUser(long id) {
        users.remove(id);
    }
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}
