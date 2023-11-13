package com.wholesale.specialintegration.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WholeException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;
    private int  code;
    private String nativeMessage;
    public WholeException() {
        super();
    }

    public WholeException(int code, String message, String nativeMessage) {
        super(message);
        this.code = code;
        this.nativeMessage = nativeMessage;
    }

    public WholeException(int code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.nativeMessage = cause.toString();
    }

    public WholeException(Throwable cause) {
        super(cause);
    }

    public WholeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
