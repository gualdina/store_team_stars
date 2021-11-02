package com.bootcamp.store.service;

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
    private final InvoiceService invoiceService;

    public ProductService(ProductRepository productRepository, InvoiceService invoiceService) {
        this.productRepository = productRepository;
        this.invoiceService = invoiceService;
    }
    //get all
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //find by id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }
   //create product
    //add Product to invoice
    public Product addProductToInvoice(Long invoiceId, Long productId){
        Product product = this.getProductById(productId);
        Invoice invoice = this.invoiceService.getInvoiceById(invoiceId);
        invoice.getInvoiceWithProducts().add(product);
        return product;
    }
    //remove product from invoice
    public void removeProductToInvoice(Long invoiceId, Long productId){
        Product product = getProductById(productId);
        Invoice invoice = this.invoiceService.getInvoiceById(invoiceId);
        invoice.getInvoiceWithProducts().remove(product);
    }
}
