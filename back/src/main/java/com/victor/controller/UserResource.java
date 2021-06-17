package com.victor.controller;

import com.victor.config.ApiVersion;
import com.victor.converter.UserDtoToUserConverter;
import com.victor.converter.UserToUserDtoConverter;
import com.victor.exception.ResourceNotFoundException;
import com.victor.model.User;
import com.victor.model.dto.UserDto;
import com.victor.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = UserResource.BASE_URL)
@Validated
public class UserResource {

    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);
    public static final String BASE_URL = "/user" + ApiVersion.Version_1_0;

    private final UserServiceImpl userService;
    private final UserDtoToUserConverter converterToUser;
    private final UserToUserDtoConverter converterToUserDto;


    public UserResource(UserServiceImpl userService, UserDtoToUserConverter converterToUser, UserToUserDtoConverter converterToUserDto) {
        this.userService = userService;
        this.converterToUser = converterToUser;
        this.converterToUserDto = converterToUserDto;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        LOG.info("In User Resource - request to get the user by id: " + id);
        if (id == null) {
            throw new ResourceNotFoundException("In User Resource - there is a request to search for a User with id null");
        }
        User user = userService.getUserById(id);
        return converterToUserDto.convert(user);
    }

    @GetMapping("/all")
    public List<UserDto> findAllUsersDto() {
        LOG.info("In User Resource - request to find all users");
        List<User> listUsers = userService.findAllUsers();
        return converterToUserDto.convertList(listUsers);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        LOG.info("In User Resource - request to delete the user by id: " + id);
        if (id != null) {
            userService.deleteUserById(id);
        } else {
            throw new ResourceNotFoundException("In User Resource - there is a request to delete User with id null");
        }
    }

    @PutMapping("/update")
    public void updateUser(@Valid UserDto userDto) {
        LOG.info("In User Resource - request to update the user: " + userDto);
        if (userDto == null) {
            throw new ResourceNotFoundException("In User Resource - there is a request to update User null");
        }
        User user = converterToUser.convert(userDto);
        userService.updateUser(user);
    }

    @PostMapping("/create")
    public void addUser(@Valid UserDto userDto) {
        LOG.info("In User Resource - request to create the user: " + userDto);
        if (userDto == null) {
            throw new ResourceNotFoundException("In User Resource - there is a request to update User null");
        }
        User user = converterToUser.convert(userDto);
        userService.createUser(user);
    }
}