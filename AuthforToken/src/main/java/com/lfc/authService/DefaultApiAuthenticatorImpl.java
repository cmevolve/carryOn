package com.lfc.authService;

import com.lfc.auth.ApiRequest;
import com.lfc.auth.AuthToken;
import com.lfc.authDao.ApiAuthenticatorDao;
import com.lfc.authDao.ApiAuthenticatorDaoImpl;

public class DefaultApiAuthenticatorImpl implements ApiAuthenticator {
    private ApiAuthenticatorDao apiAuthenticatorDao;
    public DefaultApiAuthenticatorImpl() {
        this.apiAuthenticatorDao = new ApiAuthenticatorDaoImpl();
    }
    public DefaultApiAuthenticatorImpl(ApiAuthenticatorDao credentialStorage) {
        this.apiAuthenticatorDao = credentialStorage;
    }
    @Override
    public void auth(String url) {
        System.out.println("开始登陆");
        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
        auth(apiRequest);
        System.out.println("登陆成功");
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long timestamp = apiRequest.getTimestamp();
        String originalUrl = apiRequest.getUrl();
        AuthToken authToken = new AuthToken(token,timestamp);
        if (authToken.isExpired()){
            throw new RuntimeException("Token is expired.");
        }
        String pwd = apiAuthenticatorDao.getPasswordByAppId(appId);
        AuthToken serverAuthToken = AuthToken.generate(originalUrl, timestamp,appId, pwd);
        if (!serverAuthToken.match(authToken)){
            throw new RuntimeException("Token verfication failed.");
        }
    }
}
