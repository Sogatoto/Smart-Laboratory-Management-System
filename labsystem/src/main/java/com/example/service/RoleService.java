package com.example.service;

import com.example.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRoleList();

    void updateRoleList(Role role);

    void deleteRoleList(Integer id);
}
