package com.example.service;

import com.example.pojo.Right;

import java.util.List;

public interface RightService {
    public List<Right> getRightList();

    void updateRightList(Right right);

    void deleteRightList(Integer id);
}
