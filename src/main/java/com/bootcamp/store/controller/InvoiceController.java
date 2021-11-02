package com.bootcamp.store.controller;

import com.bootcamp.store.controller.request.CreateInvoiceRequest;
import com.bootcamp.store.controller.request.InvoiceRequest;
import com.bootcamp.store.controller.response.InvoiceResponse;
import com.bootcamp.store.model.Invoice;
import com.bootcamp.store.service.InvoiceService;
import com.bootcamp.store.service.ProductService;
import com.bootcamp.store.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    public List<InvoiceResponse> invoiceResponses(List<Invoice> invoices){
        List<InvoiceResponse> invoiceResponses = new ArrayList<>();
        for(Invoice invoice : invoices){invoiceResponses.add(invoice.invoiceResponses()); }
        return invoiceResponses;
    }
   //get all invoices
   @GetMapping("/invoices")
   public List<InvoiceResponse> getAllInvoices(){
       return this.invoiceResponses(invoiceService.getAllInvoices());
   }
  //find by id
   @GetMapping("/invoice/{id}")
    public InvoiceResponse getInvoiceById(@PathVariable(value = "id") Long id){
        return invoiceService.getInvoiceById(id).invoiceResponses();
   }
   //create invoice
   @PostMapping(value = "/invoice", consumes = "application/json")
   public InvoiceResponse createInvoice(@RequestBody CreateInvoiceRequest createInvoiceRequest){
       return invoiceService.createInvoice(createInvoiceRequest.getUserId(), createInvoiceRequest.getProductIdList()).invoiceResponses();
   }
   //add invoice to user
    @PutMapping(value = "/invoice")
       public InvoiceResponse addInvoice(@PathVariable(value = "id") Long invoiceId, Long userId){
           return invoiceService.addInvoiceToUser(userId, invoiceId).invoiceResponses();
    }
    //remove invoice from user
    @DeleteMapping(value = "/invoice/{id}/user-delete/{id}")
    public void removeInvoiceFromUser(@PathVariable(value = "id")  Long invoiceId, Long userId){
        invoiceService.removeInvoiceFromUser(userId, invoiceId);
    }
    //add product to invoice
    @PutMapping(value = "/product/{id}/user/{id}")
    public InvoiceResponse addProductToUser(@PathVariable(value = "id") Long invoiceId, Long productId){
        return invoiceService.addInvoiceToProduct(productId, invoiceId).invoiceResponses();
    }
    //remove product from invoice
    @DeleteMapping(value = "/invoice/{id}/product-delete/{id}")
    public void removeProductFromUser(@PathVariable(value = "id") Long invoiceId, Long productId){
         invoiceService.addInvoiceToProduct(productId, invoiceId);
    }

}
