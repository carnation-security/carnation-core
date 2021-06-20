package com.violetfreesia.carnation.handler;

import com.violetfreesia.carnation.config.CarnationConfiguration;
import com.violetfreesia.carnation.config.ICarnationProperties;
import com.violetfreesia.carnation.support.UserInfo;
import com.violetfreesia.carnation.util.CarnationUtil;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 授权时的Token处理器, 包括Token的获取方式, 校验方式
 *
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface AuthorizeTokenHandler {

    /**
     * 用于检验Token是否有效
     * Token有效则将该Token对应的用户信息返回回来, 否则返回null
     *
     * @param token           token
     * @param carnationConfiguration 配置项
     * @return 返回用户信息
     */
    Optional<UserInfo<?, ?>> checkToken(@NonNull String token, CarnationConfiguration carnationConfiguration);

    /**
     * 用于从{@link HttpServletRequest} 中获取Token
     *
     * @param request             请求对象
     * @param ICarnationProperties 框架配置
     * @return 返回Token
     */
    default Optional<String> getToken(HttpServletRequest request, ICarnationProperties ICarnationProperties) {
        switch (ICarnationProperties.getTokenPosition()) {
            case HEADER:
                return Optional.ofNullable(request.getHeader(ICarnationProperties.getTokenName()));
            case COOKIE:
                return CarnationUtil.getCookie(request, ICarnationProperties.getTokenName());
            case Parameter:
                return Optional.ofNullable(request.getParameter(ICarnationProperties.getTokenName()));
            default:
                return Optional.empty();
        }
    }

}
