package com.dinu.bookShopManagement.service;


import com.dinu.bookShopManagement.entity.Author;

import java.util.List;

public interface AuthorService {

    public List<Author> findAllAuthors();

    public Author findAuthorById(Long id);

    public void createAuthor(Author author);

    public void updateAuthor(Author author);

    public void deleteAuthor(Long id);
}
