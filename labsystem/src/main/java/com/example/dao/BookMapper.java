package com.example.dao;

import com.example.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    public void addBookList(Book book);

    List<Book> getBookList(Book book);

    void deleteBookList(Integer id);

    void updateBookList(Book book);
}
