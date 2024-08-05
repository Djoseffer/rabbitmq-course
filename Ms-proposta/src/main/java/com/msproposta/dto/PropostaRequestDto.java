package com.msproposta.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropostaRequestDto {

    private String nome;
    private String sobrenome;
    private String valorSolicitado;
    private String telefone;
    private Integer prazoPagamento;
    private String cpf;
    private Double renda;
}
