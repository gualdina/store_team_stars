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

    @ManyToMany
    @JoinTable(
            name = "invoiceWithProducts",
            joinColumns = @JoinColumn(name= "invoice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> invoiceWithProducts = new ArrayList<>();

    @JsonIgnore
    public InvoiceResponse invoiceResponses() {
            List<ProductResponse> productResponseArrayList = new ArrayList<>();
            if(!invoiceWithProducts.isEmpty()){
                for(Product product : invoiceWithProducts){
                    productResponseArrayList.add(product.productResponses());
                }
            }
            return new InvoiceResponse(
                    this.id,
                    this.number,
                    this.total,
                    this.invoiceResponses().getUserId(),
                    productResponseArrayList
            );
    }
}
