package com.bootcamp.store.service;

import com.bootcamp.store.controller.request.InvoiceRequest;
import com.bootcamp.store.exception.InvoiceNotFound;
import com.bootcamp.store.model.Invoice;
import com.bootcamp.store.model.Product;
import com.bootcamp.store.model.User;
import com.bootcamp.store.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ProductService productService;
    private final UserService userService;

    public InvoiceService(InvoiceRepository invoiceRepository, ProductService productService, UserService userService) {
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
        this.userService = userService;
    }

    //find all
    public List<Invoice> getAllInvoices() { return invoiceRepository.findAll();}
    //find by id
    public Invoice getInvoiceById(Long id){
        return invoiceRepository.findById(id).orElseThrow(InvoiceNotFound::new);
    }
    //create invoice
    public Invoice createInvoice(InvoiceRequest invoiceRequest){
        int number = invoiceRequest.getNumber();
        double total = invoiceRequest.getTotal();
        Invoice invoice = Invoice
                .builder()
                .number(number)
                .total(total)
                .build();
        return invoiceRepository.save(invoice);
    }
    //add invoice to User
    public Invoice addInvoiceToUser(Long userId, Long invoiceId){
        Invoice invoice = this.getInvoiceById(invoiceId);
        User user = this.userService.getUserById(userId);
        user.getInvoices().add(invoice);
        return invoice;
    }
    //remove invoice from User
    public void removeInvoiceFromUser(Long userId, Long invoiceId){
        Invoice invoice = this.getInvoiceById(invoiceId);
        User user = this.userService.getUserById(userId);
        user.getInvoices().remove(invoice);
    }
    //add product to invoice
    public Invoice addInvoiceToProduct(Long productId, Long invoiceId){
        Invoice invoice = this.getInvoiceById(invoiceId);
        Product product = this.productService.getProductById(productId);
        product.getProductsOnInvoices().add(invoice);
        return invoice;
    }
    // remove product from invoice
    public void removeInvoiceFromProduct(Long productId, Long invoiceId){
        Invoice invoice = this.getInvoiceById(invoiceId);
        Product product = this.productService.getProductById(productId);
        product.getProductsOnInvoices().remove(invoice);
    }
}