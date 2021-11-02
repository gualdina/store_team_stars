package com.bootcamp.store.controller.response;

import com.bootcamp.store.model.Product;
import com.bootcamp.store.model.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private Long id;
    private int number;
    private double total;
    private User userId;
    private List<ProductResponse> productResponses;
}
