package com.qbs.app.controllers;

import com.qbs.app.model.AppUser;
import com.qbs.app.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class AppUserController {

    private final AppUserRepository userRepository;

    @QueryMapping
    Iterable<AppUser> users() {
        return userRepository.findAll();
    }
}
