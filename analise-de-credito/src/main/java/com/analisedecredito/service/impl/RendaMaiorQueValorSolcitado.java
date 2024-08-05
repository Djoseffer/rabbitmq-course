package com.analisedecredito.service.impl;

import com.analisedecredito.domain.Proposta;
import com.analisedecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class RendaMaiorQueValorSolcitado implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
    }

    private boolean rendaMaiorValorSolicitado(Proposta proposta) {
        return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
    }
}
