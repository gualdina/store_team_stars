package com.bootcamp.store.controller;

import com.bootcamp.store.controller.request.InvoiceRequest;
import com.bootcamp.store.controller.response.InvoiceResponse;
import com.bootcamp.store.controller.response.UserResponse;
import com.bootcamp.store.model.Invoice;
import com.bootcamp.store.model.User;
import com.bootcamp.store.service.InvoiceService;
import com.bootcamp.store.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class InvoiceController {
    private  InvoiceService invoiceService;
    private UserService userService;
    public InvoiceController(InvoiceService invoiceService, UserService userService) {
        this.invoiceService = invoiceService;
        this.userService = userService;
    }
    //get all invoices
   @GetMapping("/Invoices")
   public List<InvoiceResponse> getAllInvoice(){
        List<Invoice> invoiceList = invoiceService.getAllInvoices();
        List<InvoiceResponse> invoiceResponseList = new ArrayList<>();
        for( Invoice invoice : invoiceList) {
            final InvoiceResponse invoiceResponse = new InvoiceResponse(
            invoice.getId(),
            invoice.getNumber(),
            invoice.getTotal(),
            invoice.getInvoiceWithUser(),
            invoice.getInvoiceWithProducts());
            invoiceResponseList.add(invoiceResponse);
        }
        return invoiceResponseList;
   }
    //find by id
    @GetMapping("/invoices/{id]")
    public InvoiceResponse getInvoiceById(Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        return new InvoiceResponse(
                invoice.getId(),
                invoice.getNumber(),
                invoice.getTotal(),
                invoice.getInvoiceWithUser(),
                invoice.getInvoiceWithProducts());
    }
    //create invoice
    @PostMapping(value = "/invoice" , consumes = "application/json", produces = "application/json")
    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
    User user = userService.getUserById(invoiceRequest.getUserId().getId());
    Invoice invoice = invoiceService.createInvoice(invoiceRequest);
            .builder()
            .number(invoiceRequest.getNumber())
            .total(invoiceRequest.getTotal())
            .invoiceWithUser(invoiceRequest.getUserId())
            .invoiceWithProducts(invoiceRequest.getInvoiceWithProducts())
            .build();

    }
    //add invoice to user
    //remove invoice from uder
    //add product to invoice
    //remove product from invoice



}
