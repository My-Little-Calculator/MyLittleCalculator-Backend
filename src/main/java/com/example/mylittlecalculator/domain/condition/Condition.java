package com.example.mylittlecalculator.domain.condition;

import com.example.mylittlecalculator.domain.strategy.Strategy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "condition")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String formula;

    @Column(nullable = false)
    private String result;

    @ManyToOne
    @JoinColumn(nullable = false, name = "strategyId")
    private Strategy strategy;
}
