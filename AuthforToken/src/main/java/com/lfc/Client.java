package com.lfc;

import com.lfc.authService.ApiAuthenticator;
import com.lfc.authService.DefaultApiAuthenticatorImpl;

public class Client {
    public static void main(String[] args) {
        String req = "doLogin?"
                + "AppID=tom"
                + "&Token=xxty6vVxQDuW6r2by9xR29kZRk4="
                + "&Timestamp=1578306433558";

        ApiAuthenticator authencator = new DefaultApiAuthenticatorImpl();
        authencator.auth(req);
    }
}
