package com.example.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private Integer id;
    private Integer lab_id;
    private Date book_time;
    private Integer book_class;
    private String book_reason;
    private String book_username;
    private Integer book_state;
    private Lab lab;
}
