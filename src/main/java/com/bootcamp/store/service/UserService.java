package com.bootcamp.store.service;

import com.bootcamp.store.controller.request.UserRequest;
import com.bootcamp.store.exception.UserNotFound;
import com.bootcamp.store.model.Invoice;
import com.bootcamp.store.model.User;
import org.springframework.stereotype.Service;
import com.bootcamp.store.repository.UserRepository;
@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository, InvoiceService invoiceService) {
        this.userRepository = userRepository;
    }

    //get by id
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }
    //get name
    public User getUserByName(String userName){
        final var optionalUser = userRepository.findUserByName(userName);
            return optionalUser.get();
    }

    //update user
    public User updateUser(Long id, UserRequest userRequest) {
        User userToEdit = getUserById(id);
        userToEdit.setUserName(userRequest.getUserName());
        userToEdit.setAge(userRequest.getAge());
        userToEdit.setPassword(userRequest.getPassword());
        userToEdit.setActive(userRequest.isActive());
        userToEdit.setRoles(userRequest.getRoles());
        return userToEdit;
    }
}
