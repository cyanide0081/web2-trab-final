package com.mercado.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;

    @NotNull(message = "nome do produto é obrigatório")
    @NotBlank(message = "nome do produto não pode ser vazio")
    private String nomeProduto;

    @ManyToMany(mappedBy = "produtos")
    @JsonIgnore
    private List<Setor> setores;
}
