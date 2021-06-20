package com.violetfreesia.carnation.support;

import com.violetfreesia.carnation.util.CarnationUtil;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface Token {

    /**
     * 获取token值
     *
     * @return token
     */
    String getToken();

    /**
     * 刷新token
     *
     * @return refreshToken
     */
    String getRefreshToken();

    /**
     * tokenKey
     *
     * @param prefix 前缀
     * @return tokenKey
     */
    default String getTokenKey(@NonNull String prefix) {
        return CarnationUtil.joinKey(prefix, getToken());
    }

    /**
     * refreshTokenKey
     *
     * @param prefix 前缀
     * @return refreshTokenKey
     */
    default String getRefreshTokenKey(@NonNull String prefix) {
        return CarnationUtil.joinKey(prefix, getRefreshToken());
    }

    /**
     * 获取RefreshTokenInfo
     *
     * @param userId 用户编号
     * @return 返回RefreshTokenInfo
     */
    RefreshTokenInfo getRefreshTokenInfo(@NonNull Serializable userId);
}
