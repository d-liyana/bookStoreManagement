package com.dinu.bookShopManagement.controller;

import com.dinu.bookShopManagement.entity.Author;
import com.dinu.bookShopManagement.service.AuthorService;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
public class AuthorController {

    private final AuthorService authorService;

    Logger logger = Logger.getLogger(BookController.class);

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String findAllAuthors(Model model) {
        logger.info("authors");
        final List<Author> authors = authorService.findAllAuthors();

        model.addAttribute("authors", authors);
        return "list/listAuthors";
    }

    @RequestMapping("/author/{id}")
    public String findAuthorById(@PathVariable("id") Long id, Model model) {
        final Author author = authorService.findAuthorById(id);

        model.addAttribute("author", author);
        return "list/listAuthors";
    }

    @GetMapping("/addAuthor")
    public String showCreateForm(Author author) {
        return "add/addAuthor";
    }

    @RequestMapping("/add-author")
    public String createAuthor(Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add/addAuthor";
        }

        authorService.createAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/update-author/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Author author = authorService.findAuthorById(id);

        model.addAttribute("author", author);
        return "update/updateAuthor";
    }

    @RequestMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable("id") Long id, Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            author.setId(id);
            return "update/updateAuthor";
        }

        authorService.updateAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @RequestMapping("/remove-author/{id}")
    public String deleteAuthor(@PathVariable("id") Long id, Model model) {
        authorService.deleteAuthor(id);

        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }


}
