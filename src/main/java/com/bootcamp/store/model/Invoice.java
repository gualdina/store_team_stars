package com.bootcamp.store.model;

import com.bootcamp.store.controller.response.InvoiceResponse;
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
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int number;
    private double total;
    @ManyToOne
    @JoinColumn(name="invoice_id")
    private User invoiceWithUser = new User();
    @ManyToMany(mappedBy = "productsOnInvoices")
    List<Product> invoiceWithProducts = new ArrayList<>();

    @JsonIgnore
    public InvoiceResponse invoiceResponse() {
        return new InvoiceResponse(
               this.getId(),
               this.getNumber(),
               this.getTotal(),
                this.getInvoiceWithUser(),
                this.invoiceResponse().getProductResponses());
    }
}
