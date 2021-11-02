package com.bootcamp.store.controller.request;

import com.bootcamp.store.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {
    private String userName;
    private int age;
    private String password;
    private boolean active;
    private String roles;

    @JsonIgnore
    public User userCompose(){
        return User.builder()
                .userName(this.getUserName())
                .age(this.getAge())
                .password(this.getPassword())
                .active(this.isActive())
                .roles(this.getRoles())
                .invoices(new ArrayList<>())
                .build();
    }
    @JsonIgnore
    public User userIdentifier(Long id){
        return User.builder()
                .id(id)
                .userName(this.getUserName())
                .age(this.getAge())
                .password(this.getPassword())
    }
}
