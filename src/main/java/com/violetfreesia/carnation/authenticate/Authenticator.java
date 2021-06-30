package com.violetfreesia.carnation.authenticate;

import com.violetfreesia.carnation.config.CarnationConfiguration;
import com.violetfreesia.carnation.config.ICarnationProperties;
import com.violetfreesia.carnation.context.SecurityContextHolder;
import com.violetfreesia.carnation.exception.EmptyStringException;
import com.violetfreesia.carnation.exception.NotAllowedNullException;
import com.violetfreesia.carnation.exception.TokenInvalidException;
import com.violetfreesia.carnation.exception.TokenNotFoundException;
import com.violetfreesia.carnation.handler.AuthenticateTokenHandler;
import com.violetfreesia.carnation.support.RefreshTokenInfo;
import com.violetfreesia.carnation.support.Token;
import com.violetfreesia.carnation.support.UserInfo;
import com.violetfreesia.carnation.support.UserInfoGetter;
import com.violetfreesia.carnation.util.CarnationAssert;
import com.violetfreesia.carnation.util.CarnationUtil;
import lombok.NonNull;

/**
 * 认证器
 *
 * @author violetfreesia
 * @date 2021-04-26
 */
public class Authenticator {

    private final CarnationConfiguration carnationConfiguration;

    public Authenticator(@NonNull CarnationConfiguration carnationConfiguration) {
        this.carnationConfiguration = carnationConfiguration;
    }

    /**
     * 用户登录
     *
     * @param userInfo 用户信息
     * @return 登录成功的token
     */
    public Token login(@NonNull UserInfo<?, ?> userInfo) {
        return login(userInfo, null);
    }

    /**
     * 用户登录
     *
     * @param userInfo      用户信息
     * @param tempConfigure 临时配置
     * @return 登录成功的token
     */
    public Token login(@NonNull UserInfo<?, ?> userInfo, ICarnationProperties tempConfigure) {
        CarnationAssert.notNull(userInfo, new NotAllowedNullException("用户信息不能为Null"));
        // 获取认证Token处理器
        AuthenticateTokenHandler tokenHandler = carnationConfiguration.getAuthenticateTokenHandler();
        if (tempConfigure == null) {
            tempConfigure = carnationConfiguration.getCarnationProperties();
        } else {
            CarnationUtil.updateObject(carnationConfiguration.getCarnationProperties(), tempConfigure);
        }
        // 生成token
        Token token = carnationConfiguration.getTokenGenerator()
                .generateToken(userInfo, tempConfigure.getEnableRefreshToken());

        // 保存token
        if (tempConfigure.getEnableRefreshToken()) {
            tokenHandler.saveToken(token.getTokenKey(tempConfigure.getTokenPrefix()), userInfo,
                    token.getRefreshTokenKey(tempConfigure.getRefreshTokenPrefix()),
                    token.getRefreshTokenInfo(userInfo.getUserId()), tempConfigure);
        } else {
            tokenHandler.saveToken(token.getTokenKey(tempConfigure.getTokenPrefix()), userInfo, tempConfigure);
        }
        return token;
    }

    /**
     * 刷新token
     *
     * @param refreshToken   refreshToken
     * @param userInfoGetter userInfoGetter
     * @return 新token
     */
    public Token updateToken(@NonNull String refreshToken, @NonNull UserInfoGetter userInfoGetter) {
        return updateToken(refreshToken, null, userInfoGetter);
    }

    /**
     * 刷新token
     *
     * @param refreshToken   refreshToken
     * @param tempConfigure  tempConfigure
     * @param userInfoGetter userInfoGetter
     * @return 新token
     */
    public Token updateToken(@NonNull String refreshToken, ICarnationProperties tempConfigure,
                             @NonNull UserInfoGetter userInfoGetter) {
        if (tempConfigure == null) {
            tempConfigure = carnationConfiguration.getCarnationProperties();
        } else {
            CarnationUtil.updateObject(tempConfigure, carnationConfiguration.getCarnationProperties());
        }

        if (!tempConfigure.getEnableRefreshToken()) {
            throw new TokenInvalidException("系统已关闭刷新token功能");
        }

        AuthenticateTokenHandler tokenHandler = carnationConfiguration.getAuthenticateTokenHandler();
        String refreshTokenKey = CarnationUtil.joinKey(tempConfigure.getRefreshTokenPrefix(), refreshToken);
        // 根据refreshToken获取RefreshTokenInfo
        RefreshTokenInfo refreshTokenInfo = carnationConfiguration.getRefreshTokenInfoGetter()
                .getRefreshTokenInfo(refreshTokenKey);
        CarnationAssert.notNull(refreshTokenInfo, new TokenInvalidException("refreshToken已过期"));
        String tokenKey = CarnationUtil.joinKey(tempConfigure.getRefreshTokenPrefix(), refreshTokenInfo.getToken());

        // 根据用户编号获取用户信息
        UserInfo<?, ?> userInfo = userInfoGetter.getUserInfo(refreshTokenInfo.getUserId());

        // 重新登录
        Token token = login(userInfo, tempConfigure);
        // 删除旧token对
        tokenHandler.deleteToken(tokenKey);
        tokenHandler.deleteToken(refreshTokenKey);
        return token;
    }

    public void logout() {
        logout(carnationConfiguration.getCarnationProperties());
    }

    public void logout(@NonNull ICarnationProperties tempConfigure) {
        String token = SecurityContextHolder.getContext().getToken();
        CarnationUtil.updateObject(carnationConfiguration.getCarnationProperties(), tempConfigure);
        logout(token, tempConfigure);
    }

    public void logout(@NonNull String token, @NonNull ICarnationProperties tempConfigure) {
        CarnationAssert.notNull(token, new TokenNotFoundException("请求未携带Token, 无法退出登录"));
        CarnationAssert.notBlank(token, new EmptyStringException("Token不能为空"));

        AuthenticateTokenHandler tokenHandler = carnationConfiguration.getAuthenticateTokenHandler();
        String refreshToken = carnationConfiguration.getTokenGenerator().toRefreshToken(token);
        CarnationUtil.updateObject(carnationConfiguration.getCarnationProperties(), tempConfigure);

        // 删除token和刷新token
        tokenHandler.deleteToken(CarnationUtil.joinKey(tempConfigure.getRefreshTokenPrefix(), refreshToken));
        tokenHandler.deleteToken(CarnationUtil.joinKey(tempConfigure.getTokenPrefix(), token));
    }

    /**
     * 获取当前登录用户的token
     *
     * @return token
     */
    public String getToken() {
        return SecurityContextHolder.getContext().getToken();
    }
}
