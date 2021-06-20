package com.violetfreesia.carnation.support;

/**
 * @author violetfreesia
 * @date 2021-04-27
 */
public enum TokenPosition {
    /**
     * HTTP请求头中
     */
    HEADER,
    /**
     * HTTP请求的Cookie中
     */
    COOKIE,

    /**
     * HTTP请求参数中
     */
    Parameter,
}
