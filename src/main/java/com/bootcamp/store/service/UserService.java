package com.bootcamp.store.service;

import com.bootcamp.store.exception.UserNotFound;
import com.bootcamp.store.model.User;
import com.bootcamp.store.model.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bootcamp.store.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //get by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }
   //get by name
    public User getUserByName(String name){
        final var optionalUser = userRepository.getByUserName(name);{
            if(optionalUser.isPresent()) {
                return optionalUser.get();
            }
            throw new UserNotFound();
        }
    }

    //update User
    public User updateUser(Long id, String userName, String password, int age) {
        User user = this.getUserById(id);
        user.setUserName(userName);
        user.setPassword(password);
        user.setAge(age);
        return userRepository.save(user);
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(UserDetails::new).get();
    }
}
