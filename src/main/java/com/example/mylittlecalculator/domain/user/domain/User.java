package com.example.mylittlecalculator.domain.user.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String oauthId;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Registration registration;

    public void updateInfo(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    @Builder
    public User(String oAuthId, String email, String nickname, String registrationId) {
        this.oauthId = oAuthId;
        this.email = email;
        this.nickname = nickname;
        this.registration = Registration.valueOf(registrationId.toUpperCase());
    }
}
