package com.violetfreesia.carnation.handler;

import com.violetfreesia.carnation.exception.CarnationException;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败处理器
 *
 * @author violetfreesia
 * @date 2021-04-26
 */
public interface AuthFailureHandler {
    /**
     * 授权失败时的处理策略
     *
     * @param request   请求对象
     * @param response  响应对象
     * @param exception 导致授权失败的异常
     * @throws IOException IOException
     */
    void onFailure(@NonNull HttpServletRequest request,
                   @NonNull HttpServletResponse response,
                   @NonNull CarnationException exception) throws IOException;
}
