package com.violetfreesia.carnation.interceptor;

import com.violetfreesia.carnation.exception.AuthFailureException;
import com.violetfreesia.carnation.exception.PermissionIncorrectException;
import com.violetfreesia.carnation.exception.RoleIncorrectException;
import com.violetfreesia.carnation.support.UserInfo;
import com.violetfreesia.carnation.config.CarnationConfiguration;
import com.violetfreesia.carnation.context.SecurityContext;
import com.violetfreesia.carnation.context.SecurityContextHolder;
import com.violetfreesia.carnation.interceptor.annotation.CarnationAuth;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 角色权限检查拦截器
 *
 * @author violetfreesia
 * @date 2021-04-27
 */
@Aspect
public class CarnationAuthInterceptor {

    private final CarnationConfiguration carnationConfiguration;

    public CarnationAuthInterceptor(CarnationConfiguration carnationConfiguration) {
        this.carnationConfiguration = carnationConfiguration;
    }

    @Pointcut("@within(com.violetfreesia.carnation.interceptor.annotation.CarnationAuth)" +
            " || @annotation(com.violetfreesia.carnation.interceptor.annotation.CarnationAuth)")
    public void pointCut() {
    }

    @Before("pointCut()")
    @SuppressWarnings("unchecked")
    public void before(JoinPoint point) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (!context.isNeedInterceptor()) {
            return;
        }
        // 获取当前登录的用户信息
        UserInfo<?, ?> userInfo = context.getUserInfo();

        MethodSignature signature = (MethodSignature) point.getSignature();

        // 获取拦截到的方法
        Method method = signature.getMethod();

        // 获取方法上的认证注解
        CarnationAuth carnationAuth = method.getAnnotation(CarnationAuth.class);

        if (carnationAuth == null) {
            // 获取控制器上的认证注解
            carnationAuth = (CarnationAuth) signature.getDeclaringType()
                    .getAnnotation(CarnationAuth.class);
        }

        CarnationAuth.AuthType authType = carnationAuth.authType();

        // 获取用户角色检验的注解
        Class<? extends Annotation> checkRoleAnnotationClass = this.carnationConfiguration.getRoleCheckAnnotation();
        Annotation checkRoleAnnotation = method.getAnnotation(checkRoleAnnotationClass);
        // 检查用户角色
        final boolean checkRole = this.carnationConfiguration.getRoleChecker().checkRole(checkRoleAnnotation, userInfo);
        if (authType == CarnationAuth.AuthType.AND && !checkRole) {
            throw new RoleIncorrectException("已拒绝访问: 用户角色不正确");
        }
        // 获取用户权限检验注解
        Class<? extends Annotation> permissionCheckAnnotationClass =
                this.carnationConfiguration.getPermissionCheckAnnotation();
        Annotation checkPermissionAnnotation = method.getAnnotation(permissionCheckAnnotationClass);
        // 检查用户权限
        final boolean checkPermission = this.carnationConfiguration.getPermissionChecker()
                .checkPermission(checkPermissionAnnotation, userInfo);

        if (authType == CarnationAuth.AuthType.AND && !checkPermission) {
            throw new PermissionIncorrectException("已拒绝访问: 无访问权限");
        } else if (authType == CarnationAuth.AuthType.OR) {
            if (!checkRole && !checkPermission) {
                throw new AuthFailureException("已拒绝访问: 角色或权限不正确");
            }
        }
    }


}
