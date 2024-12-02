package com.mercado.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Cadastro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCadastro;

    @NotNull(message = "nome cadastrado é obrigatório")
    @NotBlank(message = "nome cadastrado não pode ser vazio")
    private String nome;
    @NotNull(message = "CPF cadastrado é obrigatório")
    @NotBlank(message = "CPF cadastrado não pode ser vazio")
    private String cpf;

    private String email;
    private String endereco;


    @OneToOne(mappedBy = "cadastroCliente")
    @JsonIgnore
    private Cliente cliente;
    @OneToOne(mappedBy = "cadastroFuncionario")
    @JsonIgnore
    private Funcionario funcionario;
}
