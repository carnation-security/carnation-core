package com.violetfreesia.carnation.context;

import com.violetfreesia.carnation.support.UserInfo;
import lombok.NonNull;

/**
 * @author violetfreesia
 * @date 2021-04-26
 */
public class SecurityContextImpl implements SecurityContext {

    private UserInfo<?, ?> userInfo;

    private String token;

    private boolean needInterceptor;

    public SecurityContextImpl() {
    }

    public SecurityContextImpl(UserInfo<?, ?> userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public UserInfo<?, ?> getUserInfo() {
        return this.userInfo;
    }

    @Override
    public void setUserInfo(@NonNull UserInfo<?, ?> userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean isNeedInterceptor() {
        return needInterceptor;
    }

    @Override
    public void setNeedInterceptor(boolean needInterceptor) {
        this.needInterceptor = needInterceptor;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public void setToken(@NonNull String token) {
        this.token = token;
    }
}
