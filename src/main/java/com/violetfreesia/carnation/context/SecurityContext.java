package com.violetfreesia.carnation.context;

import com.violetfreesia.carnation.support.UserInfo;
import lombok.NonNull;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface SecurityContext {
    /**
     * 获取用户信息
     *
     * @return 返回用户信息
     */
    UserInfo<?, ?> getUserInfo();

    /**
     * 设置用户信息
     *
     * @param userInfo 用户信息
     */
    void setUserInfo(@NonNull UserInfo<?, ?> userInfo);

    /**
     * 获取是否需要对请求进行拦截
     *
     * @return 是否需要
     */
    boolean isNeedInterceptor();

    /**
     * 设置是否需要对请求进行拦截
     *
     * @param needInterceptor 是否需要拦截
     */
    void setNeedInterceptor(boolean needInterceptor);

    /**
     * 获取本次请求的Token
     *
     * @return token
     */
    String getToken();

    /**
     * 设置token
     *
     * @param token token
     */
    void setToken(@NonNull String token);
}
