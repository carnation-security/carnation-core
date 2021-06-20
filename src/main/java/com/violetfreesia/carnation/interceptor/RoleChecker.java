package com.violetfreesia.carnation.interceptor;

import com.violetfreesia.carnation.support.UserInfo;
import lombok.NonNull;

import java.lang.annotation.Annotation;

/**
 * @author violetfreesia
 * @date 2021-04-28
 */
@FunctionalInterface
public interface RoleChecker {

    /**
     * 角色校验
     *
     * @param roleCheckerAnnotation 目标注解
     * @param userInfo              当前用户信息
     * @return 是否符合权限
     */
    boolean checkRole(Annotation roleCheckerAnnotation, @NonNull UserInfo<?, ?> userInfo);
}
