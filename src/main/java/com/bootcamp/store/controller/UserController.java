package com.bootcamp.store.controller;

import com.bootcamp.store.controller.request.UserRequest;
import com.bootcamp.store.controller.response.UserResponse;
import com.bootcamp.store.model.User;
import com.bootcamp.store.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class UserController{
 private final UserService userService;

 public UserController(UserService userService) {
  this.userService = userService;
 }
 public List<UserResponse> userResponses(List<User> users){
  List<UserResponse> userResponses = new ArrayList<>();
  for (User user : users){userResponses.add(user.userResponse());}
  return userResponses;
 }
 //get by id
 @GetMapping("/user/{id}")
 public UserResponse getUserById(@PathVariable(value = "id") Long id){
  return userService.getUserById(id).userResponse();
 }
 //update user
 @PutMapping(value = "/user-update")
 public UserResponse updateUser(@RequestBody Long id, UserRequest userRequest){
  return userService.updateUser(userRequest.userIdentifier(id)).userResponse();
 }
}
