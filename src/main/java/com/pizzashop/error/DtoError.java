package com.pizzashop.error;

/**
 * Created by Bartosz Pigla on 01/01/2017.
 */
public class DtoError {
    private String fieldName, message;

    public DtoError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
