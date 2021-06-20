package com.violetfreesia.carnation.exception;

/**
 * 认证失败异常
 *
 * @author violetfreesia
 * @date 2021-05-22
 */
public class AuthFailureException extends CarnationException {

    public AuthFailureException(String message) {
        super(message);
    }

    public AuthFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthFailureException(Throwable cause) {
        super(cause);
    }
}
