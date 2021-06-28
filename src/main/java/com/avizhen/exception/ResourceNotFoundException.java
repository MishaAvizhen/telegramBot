package com.avizhen.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
         super(message);
    }
    public ResourceNotFoundException(Integer id) {
        super("Resource with id: " + id + " not found");
    }

}
