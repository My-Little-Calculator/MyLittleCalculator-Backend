package com.example.mylittlecalculator.global.auth;

import com.example.mylittlecalculator.domain.user.domain.Registration;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetails implements OAuth2User {

    private Long id;
    private String email;
    private String nickname;
    private Registration registration;
    private Map<String, Object> attributes;

    public PrincipalDetails(Long id, String email, String nickname, Registration registration, Map<String, Object> attributes) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.registration = registration;
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return nickname;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
