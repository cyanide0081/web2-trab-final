package com.mercado.backend.repository;

import com.mercado.backend.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository
    extends JpaRepository<Funcionario, Integer> {}
