package com.dinu.bookShopManagement.service.impl;

import com.dinu.bookShopManagement.entity.Book;
import com.dinu.bookShopManagement.exception.BookAlreadyExitException;
import com.dinu.bookShopManagement.exception.NotFoundException;
import com.dinu.bookShopManagement.exception.UsernameNotFoundException;
import com.dinu.bookShopManagement.repository.BookRepository;
import com.dinu.bookShopManagement.service.BookService;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    Logger logger = Logger.getLogger(BookServiceImpl.class);

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    @Override
    public void createBook(Book book) {
        try {
            String bookName=book.getName();
            logger.info("Book name"+book.getName());
            if (book == null || book.getName() == "") {
                logger.info("inside if block");
                new BookAlreadyExitException("Please enter book name");
            }else if(book.getName().equals(bookRepository.search(book.getName()))){
                logger.info("inside else if block");
                new BookAlreadyExitException("This book already exits");

            }else {
                logger.info("inside else block in book saving");
                bookRepository.save(book);
            }
        } catch (Exception e) {
            logger.info("An exception caught in book saving " + e);
        }
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        logger.info("INSIDE SERVICE IMPL , DELETING BOOK ID IS " + id);
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
        bookRepository.deleteById(id);

    }
}
