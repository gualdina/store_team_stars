package com.bootcamp.store.model;

import com.bootcamp.store.controller.response.InvoiceResponse;
import com.bootcamp.store.controller.response.UserResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToMany( cascade = CascadeType.ALL )
    private List<Invoice> invoices = new ArrayList<>();

    @JsonIgnore
    public UserResponse userResponse(){
        List<InvoiceResponse> invoiceResponses = new ArrayList<>();
        if(this.invoices!=null && !this.invoices.isEmpty()){
            for(Invoice invoice : this.invoices) {
                invoiceResponses.add(invoice.invoiceResponses());
            }
        }
        return new UserResponse(
                this.getId(),
                this.getUserName(),
                this.getAge(),
                this.getPassword(),
                this.isActive(),
                this.getRoles(),
                invoiceResponses);
    }
}
