package com.example.service;

import com.example.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getUserList(User user);

    void addUserList(User user);

    void updateUserList(User user);

    void deleteUserList(Integer id);
}
