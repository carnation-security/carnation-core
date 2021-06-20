package com.violetfreesia.carnation.exception;

/**
 * @author violetfreesia
 * @date 2021-04-28
 */
public class PermissionIncorrectException extends CarnationException {
    public PermissionIncorrectException(String message) {
        super(message);
    }

    public PermissionIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionIncorrectException(Throwable cause) {
        super(cause);
    }
}
