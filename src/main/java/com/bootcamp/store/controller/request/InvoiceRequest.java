package com.bootcamp.store.controller.request;

import com.bootcamp.store.model.Invoice;
import com.bootcamp.store.model.Product;
import com.bootcamp.store.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InvoiceRequest {
    private int number;
    private double total;
    private User userId;


    @JsonIgnore
    public Invoice invoiceCompose(){
        return Invoice.builder()
                .number(this.getNumber())
                .total(this.getTotal())
                .invoiceWithUser(new User())
                .invoiceWithProducts(new ArrayList<>())
                .build();
    }
    @JsonIgnore
    public Invoice invoiceIdentifier(Long id) {
        return Invoice.builder()
                .id(id)
                .number(this.getNumber())
                .total(this.getTotal())
                .build();
    }
}
