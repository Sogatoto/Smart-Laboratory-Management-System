package com.example.controller;


import com.example.pojo.Right;
import com.example.service.RightService;
import com.example.utils.ResultOBJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi/rights")
public class RightController {

    @Autowired
    private RightService rightService;

    @GetMapping
    public List<Right> getRightList(){
        return rightService.getRightList();
    }

    // /adminapi/rights/3
    @PutMapping(value="/{id}")
    public ResultOBJ updataRightList(@PathVariable Integer id, @RequestBody Right right){
        try {
            right.setId(id);
            rightService.updateRightList(right);
            return ResultOBJ.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.UPDATE_ERROR;
        }
    }

    @DeleteMapping(value="/{id}")
    public ResultOBJ deleteRightList(@PathVariable Integer id){
        try {
            rightService.deleteRightList(id);
            return ResultOBJ.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultOBJ.DELETE_ERROR;
        }
    }
}
