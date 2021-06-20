package com.violetfreesia.carnation.config;

import com.violetfreesia.carnation.support.TokenPosition;

import java.util.concurrent.TimeUnit;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface ICarnationProperties {

    /**
     * token过期时间
     *
     * @return token过期时间
     */
    Integer getTokenExpireIn();

    /**
     * 刷新token过期时间
     *
     * @return 刷新token过期时间
     */
    Integer getRefreshTokenExpireIn();

    /**
     * 时间单位
     *
     * @return 时间单位
     */
    TimeUnit getTimeUnit();

    /**
     * token的前缀
     *
     * @return token前缀
     */
    String getTokenPrefix();

    /**
     * 刷新token前缀
     *
     * @return 刷新token前缀
     */
    String getRefreshTokenPrefix();

    /**
     * token的名称
     *
     * @return token的名称
     */
    String getTokenName();

    /**
     * token的位置
     *
     * @return token的位置
     */
    TokenPosition getTokenPosition();

    /**
     * 是否使用刷新token
     *
     * @return 是否使用
     */
    Boolean getEnableRefreshToken();

    /**
     * 是否开启跨域
     *
     * @return 是否开启
     */
    boolean isEnableCors();

    /**
     * 获取认证过滤器的的注册顺序
     *
     * @return 认证过滤器的的注册顺序
     */
    Integer getFilterOrder();

    /**
     * 设置token过期时间
     *
     * @param tokenExpireIn token过期时间
     * @return 配置
     */
    ICarnationProperties setTokenExpireIn(Integer tokenExpireIn);

    /**
     * 设置刷新token时间
     *
     * @param refreshTokenExpireIn 刷新token时间
     * @return 配置
     */
    ICarnationProperties setRefreshTokenExpireIn(Integer refreshTokenExpireIn);

    /**
     * 时间单位
     *
     * @param timeUnit 时间单位
     * @return 配置
     */
    ICarnationProperties setTimeUnit(TimeUnit timeUnit);

    /**
     * tokenPrefix
     *
     * @param tokenPrefix tokenPrefix
     * @return 配置
     */
    ICarnationProperties setTokenPrefix(String tokenPrefix);

    /**
     * refreshTokenPrefix
     *
     * @param refreshTokenPrefix refreshTokenPrefix
     * @return 配置
     */
    ICarnationProperties setRefreshTokenPrefix(String refreshTokenPrefix);

    /**
     * setTokenName
     *
     * @param tokenName setTokenName
     * @return 配置
     */
    ICarnationProperties setTokenName(String tokenName);

    /**
     * setTokenPosition
     *
     * @param tokenPosition setTokenPosition
     * @return 配置
     */
    ICarnationProperties setTokenPosition(TokenPosition tokenPosition);

    /**
     * setEnableRefreshToken
     *
     * @param enableRefreshToken setEnableRefreshToken
     * @return 配置
     */
    ICarnationProperties setEnableRefreshToken(Boolean enableRefreshToken);

    /**
     * 设置是否开启跨域
     *
     * @param enableCors 是否开启
     * @return 配置
     */
    ICarnationProperties setEnableCors(boolean enableCors);

    /**
     * 设置认证过滤器的的注册顺序
     *
     * @param filterOrder 认证过滤器的的注册顺序
     * @return 配置
     */
    ICarnationProperties setFilterOrder(Integer filterOrder);
}
