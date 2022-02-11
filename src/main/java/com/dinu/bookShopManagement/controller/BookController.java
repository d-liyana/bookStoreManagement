package com.dinu.bookShopManagement.controller;

import com.dinu.bookShopManagement.entity.Book;
import com.dinu.bookShopManagement.service.AuthorService;
import com.dinu.bookShopManagement.service.BookService;
import com.dinu.bookShopManagement.service.CategoryService;
import com.dinu.bookShopManagement.service.PublisherService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    Logger logger = Logger.getLogger(BookController.class);

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService,
                          PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @RequestMapping("/books")
    public String findAllBooks(Model model) {
        final List<Book> books = bookService.findAllBooks();
        logger.info("inside the book list");
        model.addAttribute("books", books);
       // return "list/listBooks";
        return "list/viewBookList";

    }

    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {
        final List<Book> books = bookService.searchBooks(keyword);
        logger.info("inside the book search");
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
      //  return "list/listBooks";
        return "list/viewBookList";

    }

    @RequestMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model) {
        final Book book = bookService.findBookById(id);
        logger.info("inside the book list");
        model.addAttribute("book", book);
        return "list/viewBook";
    }

    @GetMapping("/add")
    public String showCreateForm(Book book, Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "add/addBook";
    }

    @RequestMapping("/add-book")
    public String createBook(Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add/addBook";
        }

        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Book book = bookService.findBookById(id);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        logger.info("update book id is "+id);
        model.addAttribute("book", book);
        return "update/updateBook";
    }

    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update/updateBook";
        }

        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/remove/{id}")
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        final Book book = bookService.findBookById(id);
        logger.info("delete book id is "+id);
        model.addAttribute("book", book);
        return "delete/deleteBook";
    }

    @RequestMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        logger.info("Deleting book id is "+id);
        bookService.deleteBookById(id);
        return "redirect:/books";
    }


}