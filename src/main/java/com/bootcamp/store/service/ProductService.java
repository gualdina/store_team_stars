package com.bootcamp.store.service;

import com.bootcamp.store.exception.InvoiceNotFound;
import com.bootcamp.store.exception.ProductNotFound;
import com.bootcamp.store.model.Invoice;
import com.bootcamp.store.model.Product;
import com.bootcamp.store.repository.InvoiceRepository;
import com.bootcamp.store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;

    public ProductService(ProductRepository productRepository, InvoiceRepository invoiceRepository) {
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
    }
    //get all
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //find by id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }
    //find invoice by id
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(InvoiceNotFound::new);
    }
    //create product
    public Product createProduct(Product product){
       return productRepository.save(product);
   }
}
