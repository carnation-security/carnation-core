package com.violetfreesia.carnation.support;

import java.io.Serializable;

/**
 * 刷新token保存的信息
 *
 * @author violetfreesia
 * @date 2021-04-27
 */
public interface RefreshTokenInfo {
    /**
     * 获取refresh对应的Token
     *
     * @return token
     */
    String getToken();

    /**
     * 获取refresh对应的用户
     *
     * @return 用户标识
     */
    Serializable getUserId();
}
