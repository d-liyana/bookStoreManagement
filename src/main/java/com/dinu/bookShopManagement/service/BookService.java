package com.dinu.bookShopManagement.service;

import com.dinu.bookShopManagement.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAllBooks();

    public List<Book> searchBooks(String keyword);

    public Book findBookById(Long id);

    public void createBook(Book book);

    public void updateBook(Book book);

    public void deleteBookById(Long id);

   // public void findBookByNmae(String bookName);




}