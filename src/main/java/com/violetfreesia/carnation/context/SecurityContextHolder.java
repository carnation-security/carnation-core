package com.violetfreesia.carnation.context;

import com.violetfreesia.carnation.exception.NotAllowedNullException;
import com.violetfreesia.carnation.util.CarnationAssert;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public class SecurityContextHolder {

    private final static ThreadLocal<SecurityContext> SECURITY_CONTEXT = new ThreadLocal<>();

    private SecurityContextHolder() {
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    public static SecurityContext getContext() {
        SecurityContext context = SECURITY_CONTEXT.get();
        if (context == null) {
            context = new SecurityContextImpl();
            SECURITY_CONTEXT.set(context);
        }
        return context;
    }

    /**
     * 设置上下文
     *
     * @param context 上下文
     */
    public static void setContext(SecurityContext context) {
        CarnationAssert.notNull(context, new NotAllowedNullException("上下文对象不允许为Null"));
        SECURITY_CONTEXT.set(context);
    }

    /**
     * 清除上下文
     */
    public static void clearContext() {
        SECURITY_CONTEXT.remove();
    }
}
