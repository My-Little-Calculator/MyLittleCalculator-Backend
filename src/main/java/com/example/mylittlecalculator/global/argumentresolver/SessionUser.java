package com.example.mylittlecalculator.global.argumentresolver;

import com.example.mylittlecalculator.domain.user.domain.Registration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionUser {

    private Long id;
    private String email;
    private String nickname;
    private Registration registration;
}
