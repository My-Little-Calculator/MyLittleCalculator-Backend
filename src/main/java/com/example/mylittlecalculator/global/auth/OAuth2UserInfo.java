package com.example.mylittlecalculator.global.auth;

import java.util.Map;

public interface OAuth2UserInfo {
    String getEmail();
    String getOauthId();
    Map<String, Object> getAttribute();
}
