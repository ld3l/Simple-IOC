package com.simle.ioc.exceptions;

public class PreDestroyExecutionException extends ServiceInstantiationException {
    public PreDestroyExecutionException(String message) {
        super(message);
    }

    public PreDestroyExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
