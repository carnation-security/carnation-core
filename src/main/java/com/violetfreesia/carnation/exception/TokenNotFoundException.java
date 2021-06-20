package com.violetfreesia.carnation.exception;

/**
 * 未找到token异常
 *
 * @author violetfreesia
 * @date 2021-04-27
 */
public class TokenNotFoundException extends CarnationException {
    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(Throwable cause) {
        super(cause);
    }
}
