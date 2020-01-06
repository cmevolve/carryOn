package com.lfc.auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AuthToken {
    private final static long TIME_OUT_VALUE = 1 * 60 * 1000; //1min
    private long createTime;
    private long expiredTimeInterval = TIME_OUT_VALUE;
    private String token;


    public AuthToken(String token, long createTime) {
        this.createTime = createTime;
        this.token = token;
    }
    public AuthToken(String token, long createTime,long expiredTimeInterval) {
        this.createTime = createTime;
        this.token = token;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public static AuthToken generate(String originalUrl, long createTime, String userID,String pwd ){
        String val = originalUrl + userID + createTime ;
        String token = hmacSha1(val,pwd);
        return new AuthToken(token,createTime);
    }

    public String getToken(){
        return token;
    }
    private static String hmacSha1(String value, String key) {
        try {
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(value.getBytes());

            byte[] result = Base64.getEncoder().encode(rawHmac);

            return new String(result, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        String req = "doLogin" + "tom" + "1578306433558";
        System.out.println(hmacSha1(req,"123456qwert"));
    }

    /**
     * 是否超时
     * @return
     */
    public boolean isExpired(){
        if(createTime > System.currentTimeMillis() - expiredTimeInterval ) {
            return false;
        }
        return true;
    }

    public boolean match(AuthToken authToken){
        if(this.token.equals(authToken.getToken())){
            return true;
        }
        return false;
    }

}
