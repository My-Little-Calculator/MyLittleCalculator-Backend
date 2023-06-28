package com.example.mylittlecalculator.user.auth;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getEmail() {
        return attributes.get("email").toString();
    }

    public String getOAuthId() {
        return attributes.get("id").toString();
    }

    public Map<String, Object> getAttributes(){
        return this.attributes;
    }
}
