package com.bootcamp.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({InvoiceNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpErrorResponse handleGenericException(InvoiceNotFound exception) {
        return new HttpErrorResponse(
                404,
                "Invoice Not Found",
                LocalDateTime.now()
        );
    }
    @org.springframework.web.bind.annotation.ExceptionHandler({ProductNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpErrorResponse handleGenericException(ProductNotFound exception) {
        return new HttpErrorResponse(
                404,
                " Product Not Found",
                LocalDateTime.now()
        );
    }
    @org.springframework.web.bind.annotation.ExceptionHandler({UserNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpErrorResponse handleGenericException(UserNotFound exception) {
        return new HttpErrorResponse(
                404,
                " User Not Found",
                LocalDateTime.now()
        );
    }
}
