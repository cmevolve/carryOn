package com.lfc.auth;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    private String originalUrl;
    private String appId;
    private long timestamp;
    private String token;

    public String getUrl(){
        return originalUrl;
    }

    public String getToken(){
        return token;
    }

    public String getAppId(){
        return appId;
    }

    public long getTimestamp(){
        return timestamp;
    }

    public static Map splitUrl(String url) {
        if(null == url) throw new RuntimeException("URL IS NULL");
//        String baseUrl = url.substring(0,url.indexOf("?");
//        String[] vals = url.split("&");
        Map<String,String> parms = new HashMap();
        return parms;
    }

    public ApiRequest(String originalUrl, String appId, long timestamp, String token) {
        this.originalUrl = originalUrl;
        this.appId = appId;
        this.timestamp = timestamp;
        this.token = token;
    }


    public static ApiRequest buildFromUrl(String url) {
        Map<String,String> map = splitUrl(url);
        if(null == map) throw new RuntimeException(" map is null ");
//        ApiRequest apiRequest = new ApiRequest(map.get("originalUrl"),map.get("userId"),
//                Long.valueOf(map.get("timestamp")),map.get("token"));
        ApiRequest apiRequest = new ApiRequest("doLogin","tom",
                1578306433558L,"xxty6vVxQDuW6r2by9xR29kZRk4=");
        return apiRequest;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() + 10*60*1000);

    }
}
