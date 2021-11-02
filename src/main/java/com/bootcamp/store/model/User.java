package com.bootcamp.store.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private int age;
    private String password;
    private boolean active;
    private String roles;
    @OneToMany(mappedBy = "invoiceWithUser")
    private List<Invoice> invoices = new ArrayList<>();
}
