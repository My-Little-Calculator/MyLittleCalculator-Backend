package com.example.mylittlecalculator.user.auth;

import java.util.Map;

public interface OAuth2UserInfo {
    String getEmail();
    String getOAuthId();
    Map<String, Object> getAttributes();
}
