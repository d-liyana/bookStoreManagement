package com.dinu.bookShopManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    @GetMapping("/")
    public String list() {
        return "index";
    }
}
