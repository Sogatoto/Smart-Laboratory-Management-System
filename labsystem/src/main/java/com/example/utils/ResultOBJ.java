package com.example.utils;


import lombok.Data;

@Data
public class ResultOBJ {
    private Integer code;
    private String msg;
    private Object data;

    public ResultOBJ(Integer code){
        this.code = code;
    }
    public ResultOBJ(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultOBJ(Integer code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //登录成功
    public static final ResultOBJ LOGIN_SUCCESS = new ResultOBJ(SYSConstant.CODE_SUCCESS,SYSConstant.LOGIN_SUCCESS);

    //登录失败
    public static final ResultOBJ LOGIN_ERROR = new ResultOBJ(SYSConstant.CODE_ERROR,SYSConstant.LOGIN_ERROR);

    //添加成功
    public static final ResultOBJ ADD_SUCCESS = new ResultOBJ(SYSConstant.CODE_SUCCESS,SYSConstant.ADD_SUCCESS);

    //添加失败
    public static final ResultOBJ ADD_ERROR = new ResultOBJ(SYSConstant.CODE_ERROR,SYSConstant.ADD_ERROR);

    //更新成功
    public static final ResultOBJ UPDATE_SUCCESS = new ResultOBJ(SYSConstant.CODE_SUCCESS,SYSConstant.UPDATE_SUCCESS);

    //更新失败
    public static final ResultOBJ UPDATE_ERROR = new ResultOBJ(SYSConstant.CODE_ERROR,SYSConstant.UPDATE_ERROR);

    //删除成功
    public static final ResultOBJ DELETE_SUCCESS = new ResultOBJ(SYSConstant.CODE_SUCCESS,SYSConstant.DELETE_SUCCESS);

    //删除失败
    public static final ResultOBJ DELETE_ERROR = new ResultOBJ(SYSConstant.CODE_ERROR,SYSConstant.DELETE_ERROR);


}
