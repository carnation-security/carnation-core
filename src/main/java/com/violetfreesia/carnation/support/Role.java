package com.violetfreesia.carnation.support;

import java.util.Set;

/**
 * 用户角色接口
 *
 * @param <R> 角色值类型, 框架默认提供的是String类型, 可自定义为枚举
 * @param <P> 权限值类型, 框架默认提供的是String类型, 可自定义为枚举
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface Role<R, P> {

    /**
     * 获取角色的值
     *
     * @return 返回角色值
     */
    R getValue();

    /**
     * 获取角色具备的权限
     *
     * @return 权限集合
     */
    Set<? extends Permission<P>> getPermissions();
}
