package com.violetfreesia.carnation.support;

import lombok.NonNull;

/**
 * Token生成器
 * 用于生成Token和RefreshToken
 *
 * @author violetfreesia
 * @date 2021-04-29
 */
public interface TokenGenerator {
    /**
     * 根据用户信息生成Token和RefreshToken
     *
     * @param userInfo           用户信息
     * @param enableRefreshToken 是否开启刷新token
     * @return token对
     */
    Token generateToken(@NonNull UserInfo<?, ?> userInfo, boolean enableRefreshToken);

    /**
     * 根据token获取refreshToken
     *
     * @param token token
     * @return refreshToken
     */
    String toRefreshToken(@NonNull String token);

    /**
     * 根据refreshToken获取Token
     *
     * @param refreshToken refreshToken
     * @return token
     */
    String toToken(@NonNull String refreshToken);
}
