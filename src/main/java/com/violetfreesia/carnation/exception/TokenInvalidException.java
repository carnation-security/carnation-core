package com.violetfreesia.carnation.exception;

/**
 * token无效异常
 *
 * @author violetfreesia
 * @date 2021-04-27
 */
public class TokenInvalidException extends CarnationException {

    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenInvalidException(Throwable cause) {
        super(cause);
    }
}
