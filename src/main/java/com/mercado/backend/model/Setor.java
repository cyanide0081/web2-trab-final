package com.mercado.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Setor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSetor;

    @NotNull(message = "nome do setor é obrigatório")
    @NotBlank(message = "nome do setor não pode ser vazio")
    private String nomeSetor;

    @ManyToMany
    @JoinTable(
        name = "setor_produto",
        joinColumns = @JoinColumn(name = "id_setor"),
        inverseJoinColumns = @JoinColumn(name = "id_produto"),
        uniqueConstraints = @UniqueConstraint(
            name = "setor_produto_unique",
            columnNames = {"id_setor", "id_produto"}
        )
    )
    @JsonIgnore
    private List<Produto> produtos;
}
