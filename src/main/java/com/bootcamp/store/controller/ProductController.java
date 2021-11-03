package com.bootcamp.store.controller;

import com.bootcamp.store.controller.request.ProductRequest;
import com.bootcamp.store.controller.response.ProductResponse;
import com.bootcamp.store.model.Product;
import com.bootcamp.store.service.ProductService;
import com.bootcamp.store.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    public List<ProductResponse> productResponses(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(product.productResponses());
        }
        return productResponses;
    }

    //get all invoices
    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ProductResponse> getAllProduct() {
        return this.productResponses(productService.getAllProducts());
    }

    //find by id
    @GetMapping("/product/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ProductResponse getProductById(@PathVariable(value = "id") Long id) {
        return productService.getProductById(id).productResponses();
    }

    //create product
    @PostMapping(value = "/products", consumes = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest.productCompose()).productResponses();
    }

    //add product to invoice
    @PutMapping(value = "/invoice/{id}/user/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ProductResponse addProductToInvoice(@PathVariable(value = "id") Long productId, Long invoiceId) {
        return productService.addProductToInvoice(productId, invoiceId).productResponses();
    }

    //remove product from invoice
    @DeleteMapping(value = "/invoice-delete")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void removeProductsFromInvoice(@PathVariable Long productId, Long invoiceId) {
        productService.removeProductToInvoice(productId, invoiceId);
    }

}
