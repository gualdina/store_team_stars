package com.bootcamp.store.controller;

import com.bootcamp.store.controller.request.ProductRequest;
import com.bootcamp.store.controller.response.ProductResponse;
import com.bootcamp.store.model.Product;
import com.bootcamp.store.service.ProductService;
import com.bootcamp.store.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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

    //get all products
    @ApiOperation(value = "get all products",
            authorizations = { @Authorization(value="basicAuth") })
    @GetMapping("/products")
    public List<ProductResponse> getAllProduct() {

        return this.productResponses(productService.getAllProducts());
    }

    //find by id
    @ApiOperation(value = "find by id",
            authorizations = { @Authorization(value="basicAuth") })
    @GetMapping("/product/{id}")
    public ProductResponse getProductById(@PathVariable(value = "id") Long id) {
        return productService.getProductById(id).productResponses();
    }

    //create product
    @ApiOperation(value = "create product",
            authorizations = { @Authorization(value="basicAuth") })
    @PostMapping(value = "/products", consumes = "application/json")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest.productCompose()).productResponses();
    }


}
