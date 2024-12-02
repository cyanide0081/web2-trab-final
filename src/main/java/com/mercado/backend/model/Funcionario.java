package com.mercado.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Funcionario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncionario;

    @OneToOne
    @JoinColumn(name = "funcionario_cadastro")
    private Cadastro cadastroFuncionario;
}
