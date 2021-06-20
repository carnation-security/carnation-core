package com.violetfreesia.carnation.handler;

import com.violetfreesia.carnation.support.UserInfo;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权成功处理器
 *
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface AuthSuccessHandler {
    /**
     * 授权成功时的处理策略
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param userInfo 本次授权的用户信息
     */
    void onSuccess(@NonNull HttpServletRequest request,
                   @NonNull HttpServletResponse response,
                   @NonNull UserInfo<?, ?> userInfo);
}
