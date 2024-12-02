package com.mercado.backend.repository;

import com.mercado.backend.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Integer> {}
