package com.srt.splitwise.service;

import com.srt.splitwise.Models.User;

import java.util.List;

public interface UserService {
    public User save(User user);
    public User findUserById(long id);
    public void removeUserById(long id);
    public List<User> findAllUsers();
}
