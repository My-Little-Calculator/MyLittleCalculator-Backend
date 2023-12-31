package com.example.mylittlecalculator.domain.strategy;

import com.example.mylittlecalculator.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "strategy")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Strategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String formula;

    @Column(nullable = false)
    private String result;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;
}
