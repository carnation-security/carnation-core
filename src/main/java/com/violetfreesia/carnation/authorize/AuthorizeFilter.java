package com.violetfreesia.carnation.authorize;

import com.violetfreesia.carnation.config.CarnationConfiguration;
import com.violetfreesia.carnation.context.SecurityContext;
import com.violetfreesia.carnation.context.SecurityContextHolder;
import com.violetfreesia.carnation.exception.TokenInvalidException;
import com.violetfreesia.carnation.exception.TokenNotFoundException;
import com.violetfreesia.carnation.handler.AuthorizeTokenHandler;
import com.violetfreesia.carnation.support.UserInfo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public class AuthorizeFilter extends AbstractAuthorizeFilter {

    public AuthorizeFilter(CarnationConfiguration carnationConfiguration) {
        super(carnationConfiguration);
    }

    @Override
    protected void authorize(HttpServletRequest request, HttpServletResponse response,
                             FilterChain filterChain) throws ServletException, IOException {
        // 获取token处理器
        AuthorizeTokenHandler tokenHandler = this.carnationConfiguration.getAuthorizeTokenHandler();
        // 获取token
        String token = tokenHandler.getToken(request, this.carnationConfiguration.getCarnationProperties())
                .orElseThrow(new TokenNotFoundException("请求未携带token"));
        SecurityContextHolder.getContext().setToken(token);
        // 校验token的有效性
        UserInfo<?, ?> userInfo = tokenHandler.checkToken(token, this.carnationConfiguration)
                .orElseThrow(new TokenInvalidException("无效的token"));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setUserInfo(userInfo);
        this.carnationConfiguration.getAuthSuccessHandler().onSuccess(request, response, userInfo);
        filterChain.doFilter(request, response);
    }
}
