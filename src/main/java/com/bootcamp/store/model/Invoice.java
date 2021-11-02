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
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int number;
    private double total;

    @ManyToMany(mappedBy = "productsOnInvoices")
    List<Product> invoiceWithProducts = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="invoice_id")
    private User invoiceWithUser = new User();
}
