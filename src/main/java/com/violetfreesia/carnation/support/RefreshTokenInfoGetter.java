package com.violetfreesia.carnation.support;

import lombok.NonNull;

/**
 * @author violetfreesia
 * @date 2021-04-29
 */
@FunctionalInterface
public interface RefreshTokenInfoGetter {

    /**
     * 通过refreshTokenKey获取RefreshTokenInfo
     *
     * @param refreshTokenKey refreshTokenKey
     * @return RefreshTokenInfo
     */
    RefreshTokenInfo getRefreshTokenInfo(@NonNull String refreshTokenKey);
}
