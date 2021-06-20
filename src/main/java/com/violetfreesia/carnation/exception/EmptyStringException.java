package com.violetfreesia.carnation.exception;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public class EmptyStringException extends CarnationException {
    public EmptyStringException(String message) {
        super(message);
    }

    public EmptyStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyStringException(Throwable cause) {
        super(cause);
    }
}
