package com.example.mylittlecalculator.domain.calculator.domain;

import com.example.mylittlecalculator.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "calculator")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String formula;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;
}
