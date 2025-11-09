package com.srt.splitwise.service;

import com.srt.splitwise.Models.User;
import com.srt.splitwise.repo.InMemoryUserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private InMemoryUserRepo userRepo;
    public UserServiceImpl(InMemoryUserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public User save(User user) {
        return userRepo.addUser(user);

    }

    @Override
    public User findUserById(long id) {
        return userRepo.getUser(id);
    }

    @Override
    public void removeUserById(long id) {
        userRepo.removeUser(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.getAllUsers();
    }
}
