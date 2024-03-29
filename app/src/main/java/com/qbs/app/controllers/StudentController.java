package com.qbs.app.controllers;

import com.qbs.app.domain.AppUser;
import com.qbs.app.services.impl.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

  private final AppUserService userService;

  @GetMapping
  public List<AppUser> findAll() {
    return userService.findAllUsers();
  }

}
