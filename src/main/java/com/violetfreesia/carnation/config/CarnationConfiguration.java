package com.violetfreesia.carnation.config;

import com.violetfreesia.carnation.handler.AuthFailureHandler;
import com.violetfreesia.carnation.handler.AuthSuccessHandler;
import com.violetfreesia.carnation.handler.AuthenticateTokenHandler;
import com.violetfreesia.carnation.handler.AuthorizeTokenHandler;
import com.violetfreesia.carnation.interceptor.PermissionChecker;
import com.violetfreesia.carnation.interceptor.RoleChecker;
import com.violetfreesia.carnation.support.ExcludeUrlPatterns;
import com.violetfreesia.carnation.support.RefreshTokenInfoGetter;
import com.violetfreesia.carnation.support.TokenGenerator;
import com.violetfreesia.carnation.support.UserInfo;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.cors.CorsConfigurationSource;

import java.lang.annotation.Annotation;

/**
 * 框架配置
 *
 * @author violetfreesia
 * @date 2021-04-26
 */
@Data
@Accessors(chain = true)
@Builder
public class CarnationConfiguration {

    /**
     * 框架属性
     */
    private ICarnationProperties carnationProperties;

    /**
     * 授权成功处理器
     */
    private AuthSuccessHandler authSuccessHandler;

    /**
     * 授权失败处理器
     */
    private AuthFailureHandler authFailureHandler;

    /**
     * 认证token处理器
     */
    private AuthenticateTokenHandler authenticateTokenHandler;

    /**
     * 授权token处理器
     */
    private AuthorizeTokenHandler authorizeTokenHandler;

    /**
     * 角色检查器
     */
    private RoleChecker roleChecker;

    /**
     * 全局角色检查注解
     */
    private Class<? extends Annotation> roleCheckAnnotation;

    /**
     * 权限检查器
     */
    private PermissionChecker permissionChecker;

    /**
     * 全局权限检查注解
     */
    private Class<? extends Annotation> permissionCheckAnnotation;

    /**
     * 用户信息的实际类型
     */
    private Class<? extends UserInfo<?, ?>> userInfoType;

    /**
     * 刷新token信息获取器
     */
    private RefreshTokenInfoGetter refreshTokenInfoGetter;

    /**
     * token生成器
     */
    private TokenGenerator tokenGenerator;

    /**
     * 排除的url
     */
    private ExcludeUrlPatterns excludeUrlPatterns;

    /**
     * 跨域配置
     */
    private CorsConfigurationSource corsConfigurationSource;

}
