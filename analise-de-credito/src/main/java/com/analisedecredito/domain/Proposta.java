package com.analisedecredito.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposta {

    private Long id;
    private String nome;
    private Double valorSolicitado;
    private Integer prazoPagamento;
    private boolean aprovada;
    private boolean integrada;
    private String observacao;
    private Usuario usuario;
}
