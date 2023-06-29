package com.example.mylittlecalculator.global.config;

import com.example.mylittlecalculator.global.auth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/**" /** 로그인 안한 사용자가 사용할 수 있는 URL 추후 입력 */)
                        .permitAll()
                    .anyRequest() // 설정값 외에 나머지 URL
                        .authenticated() // 인증된 사용자에게만 허용
                .and() // 스프링은 기본적으로 X-Frame-Options Click Jacking 공격을 막기 위해 XFrameOptionsHeaderWriter의 최적화가 되있음.
                    .headers() // h2-console(iframe)을 사용하기 때문에 일단은 비활성화 / 배포 시 해당구문을 지워 활성화
                        .frameOptions()
                            .disable()
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 URL
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                        .userService(principalOauth2UserService);

        return http.build();
    }

    @Bean
    public SecurityFilterChain sessionChain(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)

                .sessionFixation().
                newSession() // 사용자가 인증을 시도하게 되면 새로운 새션과 아이디를 생성하며 이전의 설정 값들을 사용불가하게 합니다.

                .maximumSessions(1) // 최대 허용 세션 수
                .maxSessionsPreventsLogin(true) // 최대 허용 세션 수 일경우 추가 인증요청에 대해 (인증 실패 / 기존 세션 만료)
                .expiredUrl("/expired") // 세션 만료 시 이동 할 페이지

                .and()
                .invalidSessionUrl("/invalid"); // 세션이 유효하지 않을 때 이동할 페이지

        return http.build();
    }
}
