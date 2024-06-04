package com.example.service;


import com.example.dao.RoleMapper;
import com.example.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public void updateRoleList(Role role) {
        roleMapper.updateRoleList(role);
    }

    @Override
    public void deleteRoleList(Integer id) {
        roleMapper.deleteRoleList(id);
    }
}
