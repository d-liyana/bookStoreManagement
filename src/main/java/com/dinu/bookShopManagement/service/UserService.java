package com.dinu.bookShopManagement.service;

import com.dinu.bookShopManagement.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);


    User getUserById(Long id);

    User updateUser(User user);

    void deleteUserById(Long id);
}
