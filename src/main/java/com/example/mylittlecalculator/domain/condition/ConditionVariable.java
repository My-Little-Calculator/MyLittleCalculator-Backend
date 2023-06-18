package com.example.mylittlecalculator.domain.condition;

import com.example.mylittlecalculator.domain.variable.Variable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 복합키를 사용하지 않은 이유
 * 1. 복잡하다.
 * 2. 비즈니스와 관련없는 요소를 기본키로 정해야 시간이 지나도 더욱 유연하게 관리할 수 있다.
 * **/


@Getter
@Entity
@Table(name = "formula")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConditionVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conditionId")
    private Condition condition;

    @ManyToOne
    @JoinColumn(name = "variableId")
    private Variable variable;
}
