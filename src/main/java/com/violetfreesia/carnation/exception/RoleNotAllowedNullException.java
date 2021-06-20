package com.violetfreesia.carnation.exception;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public class RoleNotAllowedNullException extends NotAllowedNullException {
    public RoleNotAllowedNullException(String message) {
        super(message);
    }

    public RoleNotAllowedNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotAllowedNullException(Throwable cause) {
        super(cause);
    }
}
