package com.victor.service;


import com.victor.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> findAllUsers();

    void createUser(User user);

    void deleteUserById(Long id);

    void updateUser(User user);

}
