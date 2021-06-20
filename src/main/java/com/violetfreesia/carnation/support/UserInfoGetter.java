package com.violetfreesia.carnation.support;

import lombok.NonNull;

import java.io.Serializable;

/**
 * @author violetfreesia
 * @date 2021-04-29
 */
@FunctionalInterface
public interface UserInfoGetter {
    /**
     * 根据用户编号获取用户信息
     *
     * @param userId 用户编号
     * @return 用户信息
     */
    UserInfo<?, ?> getUserInfo(@NonNull Serializable userId);
}
