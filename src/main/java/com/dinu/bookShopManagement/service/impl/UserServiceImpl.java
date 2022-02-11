package com.dinu.bookShopManagement.service.impl;

import com.dinu.bookShopManagement.entity.User;
import com.dinu.bookShopManagement.exception.UsernameNotFoundException;
import com.dinu.bookShopManagement.repository.UserRepository;
import com.dinu.bookShopManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }


}
