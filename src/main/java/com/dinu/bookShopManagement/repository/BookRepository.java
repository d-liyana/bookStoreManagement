package com.dinu.bookShopManagement.repository;

import com.dinu.bookShopManagement.entity.Book;
import org.jboss.logging.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Logger logger = Logger.getLogger(BookRepository.class);

    @Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" )
    public List<Book> search(String keyword);

}
