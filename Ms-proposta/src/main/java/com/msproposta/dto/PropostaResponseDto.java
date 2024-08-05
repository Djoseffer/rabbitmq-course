package com.msproposta.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropostaResponseDto{
    private Long id;
    private  String obeservacao;
    private  Boolean aprovado;
    private  String nome;
    private  String sobrenome;
    private String valorSolicitadoFmt;
    private String telefone;
    private Integer prazoPagamento;
    private String cpf;
    private Double renda;
    }


