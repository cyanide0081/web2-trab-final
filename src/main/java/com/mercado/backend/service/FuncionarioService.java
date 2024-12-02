package com.mercado.backend.service;

import com.mercado.backend.model.Funcionario;
import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Integer id) {
        return funcionarioRepository.findById(id).orElseThrow();
    }

    public Funcionario insertFuncionario(Funcionario funcionario) {
        funcionario.setIdFuncionario(null);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario updateFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public MensagemDTO deleteFuncionario(Integer idFuncionario) {
        try {
            funcionarioRepository.findById(idFuncionario).orElseThrow();
            funcionarioRepository.deleteById(idFuncionario);
        } catch (Exception e) {
            return new MensagemDTO("ERRO", e.getMessage());
        }

        return new MensagemDTO("OK", "OK");
    }
}
