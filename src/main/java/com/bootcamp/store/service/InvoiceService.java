package com.bootcamp.store.service;

import com.bootcamp.store.exception.InvoiceNotFound;
import com.bootcamp.store.exception.ProductNotFound;
import com.bootcamp.store.exception.UserNotFound;
import com.bootcamp.store.model.Invoice;
import com.bootcamp.store.model.Product;
import com.bootcamp.store.model.User;
import com.bootcamp.store.repository.InvoiceRepository;
import com.bootcamp.store.repository.ProductRepository;
import com.bootcamp.store.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    //find all
    public List<Invoice> getAllInvoices() { return invoiceRepository.findAll();}
    //find by id
    public Invoice getInvoiceById(Long id){
        return invoiceRepository.findById(id).orElseThrow(InvoiceNotFound::new);
    }
    //find product by id
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }
    //find user by id
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }
    //create invoice
    public Invoice createInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }
    //add invoice to User
    public Invoice addInvoiceToUser(Long userId, Long invoiceId){
        Invoice invoice = this.getInvoiceById(invoiceId);
        User user = this.getUserById(userId);
        user.getInvoices().add(invoice);
        return invoice;
    }
    //remove invoice from User
    public void removeInvoiceFromUser(Long userId, Long invoiceId){
        Invoice invoice = this.getInvoiceById(invoiceId);
        User user = this.getUserById(userId);
        user.getInvoices().remove(invoice);
    }
    //add product to invoice
    public Invoice addInvoiceToProduct(Long productId, Long invoiceId){
        Invoice invoice = this.getInvoiceById(invoiceId);
        Product product = this.getProductById(productId);
        product.getProductsOnInvoices().add(invoice);
        return invoice;
    }
    // remove product from invoice
    public void removeInvoiceFromProduct(Long productId, Long invoiceId){
        Invoice invoice = this.getInvoiceById(invoiceId);
        Product product = this.getProductById(productId);
        product.getProductsOnInvoices().remove(invoice);
    }
}