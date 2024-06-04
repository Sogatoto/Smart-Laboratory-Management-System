package com.example.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
    private Integer is_default;
    private Role role;
    private String token;
}
