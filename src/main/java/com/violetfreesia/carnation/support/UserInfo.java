package com.violetfreesia.carnation.support;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @param <R> 角色值类型
 * @param <P> 权限值类型
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface UserInfo<R, P> extends Serializable {
    /**
     * 获取用户具有的权限
     *
     * @return 权限集合
     */
    Set<? extends Permission<P>> getPermissions();

    /**
     * 获取用户角色
     *
     * @return 用户角色
     */
    Role<R, P> getRole();

    /**
     * 获取用户唯一标识
     *
     * @return 用户唯一标识
     */
    Serializable getUserId();

    /**
     * 获取用户的所有权限, 包含角色的权限和用户自身的权限
     *
     * @return 权限集合
     */
    default Set<? extends Permission<P>> allPermissions() {
        HashSet<Permission<P>> allPermission = new HashSet<>(getRole().getPermissions());
        allPermission.addAll(getPermissions());
        return allPermission;
    }
}
