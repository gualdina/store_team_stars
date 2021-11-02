package com.bootcamp.store.controller.response;

import com.bootcamp.store.model.Invoice;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private double value;
    private List<Invoice> productsOnInvoices;
}
