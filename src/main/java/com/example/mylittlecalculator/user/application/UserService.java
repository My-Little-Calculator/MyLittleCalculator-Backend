package com.example.mylittlecalculator.user.application;

import com.example.mylittlecalculator.user.auth.NaverUserInfo;
import com.example.mylittlecalculator.user.auth.OAuth2UserInfo;
import com.example.mylittlecalculator.user.auth.PrincipalDetails;
import com.example.mylittlecalculator.user.domain.Registration;
import com.example.mylittlecalculator.user.domain.User;
import com.example.mylittlecalculator.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        for (String key : oAuth2User.getAttributes().keySet()) {
            log.info(key + " : " + oAuth2User.getAttribute(key));
        }

        OAuth2UserInfo oAuth2UserInfo = null;
        if (registrationId.toUpperCase().equals(Registration.NAVER.toString())) {
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttribute("response"));
        }


        if (oAuth2UserInfo == null) {
            throw new IllegalArgumentException("Registration 이 naver가 아닙니다.");
        }

        User user = userRepository.findUserByOauthId(oAuth2UserInfo.getOAuthId());

        if (user == null) {
            user = userRepository.save(User.builder()
                    .oAuthId(registrationId.toUpperCase() + "_" + oAuth2UserInfo.getOAuthId())
                    .email(oAuth2UserInfo.getEmail())
                    .nickname(UUID.randomUUID().toString().substring(1, 12))
                    .registrationId(registrationId)
                    .build());
        }

        return new PrincipalDetails(user, oAuth2UserInfo.getAttributes());
    }

}
