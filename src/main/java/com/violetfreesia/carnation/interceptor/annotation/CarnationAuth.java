package com.violetfreesia.carnation.interceptor.annotation;

import java.lang.annotation.*;

/**
 * @author violetfreesia
 * @date 2021-04-27
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CarnationAuth {

    /**
     * 权限与角色的鉴权类型, 默认为AND
     *
     * @return 鉴权类型
     */
    AuthType authType() default AuthType.AND;


    /**
     * 鉴权类型
     */
    enum AuthType {
        /**
         * 便是鉴权角色和权限需要同时满足
         */
        AND,
        /**
         * 表示鉴权角色和权限只需要满足一个
         */
        OR
    }
}
