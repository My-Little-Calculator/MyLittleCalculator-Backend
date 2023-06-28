package com.example.mylittlecalculator.variable;

import com.example.mylittlecalculator.strategy.Strategy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "variable")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String formula;

    @ManyToOne
    @JoinColumn(nullable = false, name = "strategyId")
    private Strategy strategy;
}
