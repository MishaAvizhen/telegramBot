package com.avizhen.exception;

public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(String message) {
        super(message);
    }
    public ResourceAlreadyExistException(Integer id) {
        super("Resource with id: " + id+ " already exist");
    }

}
