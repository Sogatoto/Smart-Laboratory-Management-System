package com.example.dao;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {


    public List<User> getUserList(User user);

    void addUserList(User user);

    void updateUserList(User user);

    void deleteUserList(Integer id);

}
