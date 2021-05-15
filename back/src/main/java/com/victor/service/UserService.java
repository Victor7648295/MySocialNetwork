package com.victor.service;


import com.victor.model.User;

public interface UserService {

    User getUserById(Long id);

    void createUser(User user);

    void deleteUserById(Long id);

    void updateUser(User user);

}
