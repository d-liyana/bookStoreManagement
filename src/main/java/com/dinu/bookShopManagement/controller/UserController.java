package com.dinu.bookShopManagement.controller;

import com.dinu.bookShopManagement.entity.User;
import com.dinu.bookShopManagement.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*=======================================GET methods start==========================================================*/

    @GetMapping("/login")
    public String login() {
        return "registration/login";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/viewUserList";
    }

    @GetMapping("/users/new")
    public String createUserForm(Model model) {
        // create user object to hold user form data
        User user = new User();
        model.addAttribute("user", user);
        return "admin/addUser";

    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "admin/editUser";
    }


    @GetMapping("/users/view/{id}")
    public String viewUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "admin/userDetails";
    }


    @GetMapping("/users/deleteTheUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        logger.info("id is "+id);
        userService.deleteUserById(id);
        return "redirect:/users";

    }

    /*=======================================POST methods start==========================================================*/


    @PostMapping("/users")
    public String saveUser(@ModelAttribute("users") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id,
                                @ModelAttribute("users") User user,
                                Model model) {

        // get student from database by id
        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUser_name(user.getUser_name());
        existingUser.setPassword(user.getPassword());
        existingUser.setDob(user.getDob());

        // save updated user object
        userService.updateUser(existingUser);
        return "redirect:/users";
    }


    /*===================Search===========================*/

/*
    @RequestMapping("/searchUser")
    public String searchUser(@Param("keyword") String keyword, Model model) {
        logger.info("keywords "+keyword);
        final List<User> users = userService.searchUser(keyword);
        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        return "admin/searchResult";
    }
*/


}
