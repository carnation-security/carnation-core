package com.violetfreesia.carnation.authorize;

import com.violetfreesia.carnation.config.CarnationConfiguration;
import com.violetfreesia.carnation.context.SecurityContextHolder;
import com.violetfreesia.carnation.exception.CarnationException;
import com.violetfreesia.carnation.exception.TokenInvalidException;
import com.violetfreesia.carnation.exception.TokenNotFoundException;
import com.violetfreesia.carnation.support.ExcludeUrlPatterns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsProcessor;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.DefaultCorsProcessor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public abstract class AbstractAuthorizeFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AbstractAuthorizeFilter.class);

    /**
     * ant路径匹配器
     */
    private final AntPathMatcher antPathMatcher;

    /**
     * 框架配置
     */
    protected CarnationConfiguration carnationConfiguration;

    /**
     * 跨域处理器
     */
    private CorsProcessor processor;


    public AbstractAuthorizeFilter(CarnationConfiguration carnationConfiguration) {
        this.antPathMatcher = new AntPathMatcher();
        this.carnationConfiguration = carnationConfiguration;
        if (carnationConfiguration.getCarnationProperties().isEnableCors()) {
            processor = new DefaultCorsProcessor();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        final ExcludeUrlPatterns urlPatterns = this.carnationConfiguration.getExcludeUrlPatterns();
        boolean isMatch = urlPatterns.getExcludeUrlPatterns().stream()
                .anyMatch(antPath -> antPathMatcher.match(antPath, request.getServletPath()));
        SecurityContextHolder.getContext().setNeedInterceptor(!isMatch);
        return isMatch;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 跨域处理
        if (carnationConfiguration.getCarnationProperties().isEnableCors()) {
            CorsConfiguration corsConfiguration = carnationConfiguration
                    .getCorsConfigurationSource().getCorsConfiguration(request);
            boolean isValid = this.processor.processRequest(corsConfiguration, request, response);
            if (!isValid || CorsUtils.isPreFlightRequest(request)) {
                return;
            }
        }
        try {
            authorize(request, response, filterChain);
        } catch (Exception e) {
            if (e instanceof CarnationException || e.getCause() instanceof CarnationException) {
                CarnationException temp = (CarnationException) (e instanceof CarnationException ? e : e.getCause());
                logger.debug(temp.getMessage(), temp);
                if (e instanceof TokenNotFoundException || e instanceof TokenInvalidException) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                } else {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                }
                this.carnationConfiguration.getAuthFailureHandler().onFailure(request, response, temp);
            } else {
                throw e;
            }
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    /**
     * 权限认证
     * 主要负责Token校验, 用户信息获取
     *
     * @param request     http请求
     * @param response    http响应
     * @param filterChain 过滤链
     * @throws ServletException   ServletException
     * @throws IOException        IOException
     * @throws CarnationException CarnationException
     */
    protected abstract void authorize(HttpServletRequest request, HttpServletResponse response,
                                      FilterChain filterChain) throws ServletException, IOException;
}
