package com.example.service;

import com.example.dao.RightMapper;
import com.example.pojo.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightServiceImpl implements RightService{

    @Autowired
    private RightMapper rightMapper;

    @Override
    public List<Right> getRightList() {
        return rightMapper.getRightList();
    }

    @Override
    public void updateRightList(Right right) {
        rightMapper.updateRightList(right);
    }

    @Override
    public void deleteRightList(Integer id) {
        rightMapper.deleteRightList(id);
    }
}
