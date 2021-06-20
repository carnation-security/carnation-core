package com.violetfreesia.carnation.exception;

/**
 * @author violetfreesia
 * @date 2021-04-28
 */
public class NotSupportTypeException extends CarnationException {
    public NotSupportTypeException(String message) {
        super(message);
    }

    public NotSupportTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportTypeException(Throwable cause) {
        super(cause);
    }
}
