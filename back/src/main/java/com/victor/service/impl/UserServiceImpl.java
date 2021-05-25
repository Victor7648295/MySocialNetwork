package com.victor.service.impl;

import com.victor.exception.ResourceNotFoundException;
import com.victor.model.User;
import com.victor.repository.UserRepository;
import com.victor.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
//        log.debug("In UserService - request find User with id {} ", id);
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("In UserService - not found user with id {} " + id));
    }

    @Override
    public List<User> findAllUsers() {
//        log.debug("In UserService - request find all Users");
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
//        log.debug("In UserService - successfully create user {}", user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("In UserService - not found user with id {} " + id));
        userRepository.delete(user);
//        log.debug("In UserService - successfully delete user  with id {}", id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User userInBase = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("In UserService - fail update, not found user with id {} " + user.getId()));
        if (user.getId().equals(userInBase.getId())) {
            userRepository.saveAndFlush(user);
        }
//        log.debug("In UserService - successfully update user  with id {}", user.id());
    }
}
