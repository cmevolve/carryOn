package com.lfc.authService;

import com.lfc.auth.ApiRequest;

public interface ApiAuthenticator {
    void auth(String url) ;
    void auth(ApiRequest apiRequest);
}
