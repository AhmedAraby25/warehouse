package com.ikea.warehouse.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectName, long id) {
        super("Cannot find Object " + objectName + " with id : " + id);
    }
}
