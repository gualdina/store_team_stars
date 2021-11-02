package com.bootcamp.store.service;

import com.bootcamp.store.exception.UserNotFound;
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
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }
    //update user
    public User updateUser(User user) {
        User userToEdit = getUserById(user.getId());
        return userRepository.save(userToEdit);
    }
}
