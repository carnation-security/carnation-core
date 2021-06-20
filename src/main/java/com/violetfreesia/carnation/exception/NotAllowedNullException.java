package com.violetfreesia.carnation.exception;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public class NotAllowedNullException extends CarnationException {
    public NotAllowedNullException(String message) {
        super(message);
    }

    public NotAllowedNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowedNullException(Throwable cause) {
        super(cause);
    }
}
