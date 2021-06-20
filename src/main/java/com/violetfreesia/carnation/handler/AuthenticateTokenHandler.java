package com.violetfreesia.carnation.handler;

import com.violetfreesia.carnation.config.ICarnationProperties;
import com.violetfreesia.carnation.support.RefreshTokenInfo;
import com.violetfreesia.carnation.support.UserInfo;
import lombok.NonNull;

/**
 * 登录时的Token处理器, 包括Token的生成和保存
 *
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface AuthenticateTokenHandler {
    /**
     * Token的保存方式
     *
     * @param tokenKey         tokenKey
     * @param userInfo         tokenKey对应的UserInfo
     * @param refreshTokenKey  refreshTokenKey
     * @param refreshTokenInfo refreshTokenKey对应的refreshTokenInfo
     * @param properties       全局配置项
     */
    void saveToken(@NonNull String tokenKey, @NonNull UserInfo<?, ?> userInfo,
                   @NonNull String refreshTokenKey, @NonNull RefreshTokenInfo refreshTokenInfo,
                   @NonNull ICarnationProperties properties);

    /**
     * Token的保存方式
     *
     * @param tokenKey   tokenKey
     * @param userInfo   tokenKey对应的UserInfo
     * @param properties 全局配置项
     */
    void saveToken(@NonNull String tokenKey, @NonNull UserInfo<?, ?> userInfo,
                   @NonNull ICarnationProperties properties);

    /**
     * 删除token的方式
     *
     * @param tokenKey tokenKey
     */
    void deleteToken(@NonNull String tokenKey);
}
