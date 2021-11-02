package com.bootcamp.store.controller;

import com.bootcamp.store.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class UserController{
 private final UserService userService;

 public UserController(UserService userService) {
  this.userService = userService;
 }
}
