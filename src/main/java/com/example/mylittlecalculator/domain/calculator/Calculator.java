package com.example.mylittlecalculator.domain.calculator;

import com.example.mylittlecalculator.domain.user.User;
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

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;
}
