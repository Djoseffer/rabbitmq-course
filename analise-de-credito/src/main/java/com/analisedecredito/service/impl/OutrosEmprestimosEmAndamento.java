package com.analisedecredito.service.impl;

import com.analisedecredito.domain.Proposta;
import com.analisedecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OutrosEmprestimosEmAndamento implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return outrosEmprestimosEmAndamentos() ? 0 : 80;
    }

    private boolean outrosEmprestimosEmAndamentos() {
        return new Random().nextBoolean();
    }
}
