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
public class CreateInvoiceRequest {
    private Long userId;
    private int number;
    private List<Long> productIdList;

    @JsonIgnore
    public Invoice createInvoice(double total, int number, User user, List<Product> products){
        return Invoice
                .builder()
                .total(total)
                .number(number)
                .user(user)
                .invoiceWithProducts(products)
                .build();
    }

}
