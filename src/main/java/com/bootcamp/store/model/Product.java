package com.bootcamp.store.model;

import com.bootcamp.store.controller.response.ProductResponse;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double value;

    @ManyToMany
    @JoinTable(
            name = "product_invoice",
            joinColumns = @JoinColumn(name= "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id", referencedColumnName = "id"))
    List<Invoice> productsOnInvoices = new ArrayList<>();

    @JsonIgnore
    public ProductResponse productResponse(){
        return new ProductResponse(
                this.getId(),
                this.getName(),
                this.getValue(),
                this.productResponse().getInvoiceResponseList());
    }
}

