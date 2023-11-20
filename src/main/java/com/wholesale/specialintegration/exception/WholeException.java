package com.wholesale.specialintegration.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class WholeException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Setter
    private String message;

    public WholeException(int code, String message) {
        super(message);
    }
}
