package com.gr.RecruMe.exceptions;

/**
 * Exception thrown when requested object does not exist
 */
public class NotFoundException extends Exception{
    public NotFoundException(String description) {
        super(description);
    }
}
