package com.analisedecredito.service.strategy;

import com.analisedecredito.domain.Proposta;

public interface CalculoPonto {

    int calcular(Proposta proposta);
}
