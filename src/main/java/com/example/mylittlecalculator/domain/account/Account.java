package com.example.mylittlecalculator.domain.account;

import com.example.mylittlecalculator.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 식별관계를 사용하지 않은 이유 :
 * 식별 관계에서 자식 테이블은 부모 테이블의 기본 키를 자신의 기본 키로 사용함으로 테이블 구조가 유연하지 못함.
 * **/

@Getter
@Entity
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String kakaoOauthKey;

    @Column(nullable = true)
    private String naverOauthKey;

    @OneToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;
}
