package com.dinu.bookShopManagement.controller;

import com.dinu.bookShopManagement.entity.Publisher;
import com.dinu.bookShopManagement.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Controller
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @RequestMapping("/publishers")
    public String findAllPublishers(Model model) {
        final List<Publisher> publishers = publisherService.findAllPublishers();

        model.addAttribute("publishers", publishers);
        return "list/listPublishers";
    }

    @RequestMapping("/publisher/{id}")
    public String findPublisherById(@PathVariable("id") Long id, Model model) {
        final Publisher publisher = publisherService.findPublisherById(id);

        model.addAttribute("publisher", publisher);
        return "list/viewPublisher";
    }

    @GetMapping("/addPublisher")
    public String showCreateForm(Publisher publisher) {
        return "add/addPublisher";
    }

    @RequestMapping("/add-publisher")
    public String createPublisher(Publisher publisher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add/addPublisher";
        }

        publisherService.createPublisher(publisher);
        model.addAttribute("publisher", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

    @GetMapping("/updatePublisher/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Publisher publisher = publisherService.findPublisherById(id);

        model.addAttribute("publisher", publisher);
        return "update/updatePublisher";
    }

    @RequestMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            publisher.setId(id);
            return "update/updatePublishers";
        }

        publisherService.updatePublisher(publisher);
        model.addAttribute("publisher", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

    @RequestMapping("/remove-publisher/{id}")
    public String deletePublisher(@PathVariable("id") Long id, Model model) {
        publisherService.deletePublisher(id);

        model.addAttribute("publisher", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

}
