package com.example.mylittlecalculator.global.auth;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes){
        this.attributes = attributes;
    }

    public String getEmail() {
        return attributes.get("email").toString();
    }

    public String getOauthId() {
        return attributes.get("id").toString();
    }

    @Override
    public Map<String, Object> getAttribute() {
        return this.attributes;
    }

}
