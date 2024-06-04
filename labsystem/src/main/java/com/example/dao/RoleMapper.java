package com.example.dao;


import com.example.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    public List<Role> getRoleList();

    void updateRoleList(Role role);

    void deleteRoleList(Integer id);
}
