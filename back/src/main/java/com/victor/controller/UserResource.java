package com.victor.controller;

import com.victor.config.ApiVersion;
import com.victor.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = UserResource.BASE_URL)
@AllArgsConstructor
@Slf4j
public class UserResource {

    public static final String BASE_URL = "/user" + ApiVersion.Version_1_0;

    private final UserServiceImpl userService;
}
