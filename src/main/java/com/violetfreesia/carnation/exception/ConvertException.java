package com.violetfreesia.carnation.exception;

/**
 * @author violetfreesia
 * @date 2021-04-27
 */
public class ConvertException extends CarnationException {
    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }
}
