package com.violetfreesia.carnation.exception;

/**
 * @author violetfreesia
 * @date 2021-04-28
 */
public class RoleIncorrectException extends CarnationException {
    public RoleIncorrectException(String message) {
        super(message);
    }

    public RoleIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleIncorrectException(Throwable cause) {
        super(cause);
    }
}
