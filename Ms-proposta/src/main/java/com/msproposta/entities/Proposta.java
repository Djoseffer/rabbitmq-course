
package com.msproposta.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_proposta")
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valorSolicitado;
    private Integer prazoPagamento;
    private Boolean aprovada;
    private boolean integrada;
    private String observacao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;
}
