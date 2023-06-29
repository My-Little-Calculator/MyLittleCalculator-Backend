package com.example.mylittlecalculator.global.auth;

import com.example.mylittlecalculator.domain.user.domain.Registration;
import com.example.mylittlecalculator.domain.user.domain.User;
import com.example.mylittlecalculator.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oAuth2UserInfo;
        if(registrationId.toUpperCase().equals(Registration.KAKAO.toString())){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        else if(registrationId.toUpperCase().equals(Registration.NAVER.toString())){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttribute("response"));
        }
        else {
            throw new IllegalArgumentException("잘못된 OAuth 요청입니다.");
        }


        User user = userRepository.findUserByOauthId(oAuth2UserInfo.getOauthId());

        if (user == null) {
            user = User.builder()
                    .oAuthId(registrationId.toUpperCase() + "_" + oAuth2UserInfo.getOauthId())
                    .email(oAuth2UserInfo.getEmail())
                    .nickname(UUID.randomUUID().toString().substring(1, 12))
                    .registrationId(registrationId)
                    .build();
            user = userRepository.save(user);
        }

        return new PrincipalDetails(user.getId(),
                user.getEmail(), user.getNickname(), user.getRegistration(),
                oAuth2UserInfo.getAttribute());
    }
}
