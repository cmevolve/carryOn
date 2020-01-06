package com.lfc.authDao;

import java.lang.reflect.AnnotatedParameterizedType;

public class ApiAuthenticatorDaoImpl implements ApiAuthenticatorDao {
    @Override
    public String getPasswordByAppId(String appId) {
            return "123456qwert";
    }
}
