package com.violetfreesia.carnation.exception;

import java.util.function.Supplier;

/**
 * 权限框架顶级异常
 *
 * @author violetfreesia
 * @date 2021-04-26
 */
public class CarnationException extends RuntimeException implements Supplier<CarnationException> {

    public CarnationException(String message) {
        super(message);
    }

    public CarnationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarnationException(Throwable cause) {
        super(cause);
    }

    @Override
    public CarnationException get() {
        return this;
    }
}
