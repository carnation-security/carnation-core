package com.violetfreesia.carnation.exception;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public class PermissionNotAllowedNullException extends NotAllowedNullException {
    public PermissionNotAllowedNullException(String message) {
        super(message);
    }

    public PermissionNotAllowedNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionNotAllowedNullException(Throwable cause) {
        super(cause);
    }
}
