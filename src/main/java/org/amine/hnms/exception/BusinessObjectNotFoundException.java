package org.amine.hnms.exception;

public class BusinessObjectNotFoundException extends RuntimeException{
    public BusinessObjectNotFoundException(String message) {
        super(message);
    }
}
