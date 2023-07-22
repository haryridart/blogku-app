package com.dartacademy.blogkuapp.payload;

public class JWTAuthResponse {
    private String accessToken;
    private String tokenType="Bearer";
    public JWTAuthResponse(String accessToken){
        this.accessToken = accessToken;
    }
    public String getAccessToken(){
        return accessToken;
    }
    public String getTokenType(){
        return tokenType;
    }
}
