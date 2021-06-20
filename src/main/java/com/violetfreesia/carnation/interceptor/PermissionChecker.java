package com.violetfreesia.carnation.interceptor;

import com.violetfreesia.carnation.support.UserInfo;
import lombok.NonNull;

import java.lang.annotation.Annotation;

/**
 * @author violetfreesia
 * @date 2021-04-28
 */
public interface PermissionChecker {

    /**
     * 权限校验
     *
     * @param permissionCheckerAnnotation 权限的目标注解
     * @param userInfo                    当前用户信息
     * @return 是否符合权限
     */
    boolean checkPermission(@NonNull Annotation permissionCheckerAnnotation, @NonNull UserInfo<?, ?> userInfo);
}
