package com.bootcamp.store.controller.request;

import com.bootcamp.store.model.Product;
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
public class ProductRequest {
    private String name;
    private double value;

    @JsonIgnore
    public Product productCompose(){
        return Product.builder()
                .name(this.getName())
                .value(this.getValue())
                .productsOnInvoices(new ArrayList<>())
                .build();
    }
    @JsonIgnore
    public Product productIdentifier(Long id){
       return Product.builder()
               .id(id)
               .name(this.getName())
               .value(this.getValue())
               .build();
}
}

