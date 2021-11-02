package com.bootcamp.store.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound() {super("Coffee not found.");}
}
