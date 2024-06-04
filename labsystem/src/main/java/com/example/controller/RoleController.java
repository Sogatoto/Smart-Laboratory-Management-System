package com.example.controller;



import com.example.pojo.Role;
import com.example.service.RoleService;
import com.example.utils.ResultOBJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getRoleList(){
        return roleService.getRoleList();
    }

    @PutMapping(value="/{id}")
    public ResultOBJ updataRoleList(@PathVariable Integer id, @RequestBody Role role){
        try {
            role.setId(id);
            roleService.updateRoleList(role);
            return ResultOBJ.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.UPDATE_ERROR;
        }
    }

    @DeleteMapping(value="/{id}")
    public ResultOBJ deleteROleList(@PathVariable Integer id){
        try {
            roleService.deleteRoleList(id);
            return ResultOBJ.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.DELETE_ERROR;
        }
    }
}
