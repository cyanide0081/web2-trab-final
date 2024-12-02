package com.mercado.backend.service;

import com.mercado.backend.model.Cadastro;
import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.model.Produto;
import com.mercado.backend.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public List<Cadastro> findAll() {
        return cadastroRepository.findAll();
    }

    public Cadastro findById(Integer id) {
        return cadastroRepository.findById(id).orElseThrow();
    }

    public Cadastro insertCadastro(Cadastro cadastro) {
        cadastro.setIdCadastro(null);
        return cadastroRepository.save(cadastro);
    }

    public Cadastro updateCadastro(Cadastro cadastro) {
        Cadastro cadastroAtual = cadastroRepository
            .findById(cadastro.getIdCadastro())
            .orElseThrow();

        if (cadastro.getNome() != null) {
            cadastroAtual.setNome(cadastro.getNome());
        }

        if (cadastro.getCpf() != null) {
            cadastroAtual.setCpf(cadastro.getCpf());
        }

        if (cadastro.getEmail() != null) {
            cadastroAtual.setEmail(cadastro.getEmail());
        }

        if (cadastro.getEndereco() != null) {
            cadastroAtual.setEndereco(cadastro.getEndereco());
        }

        if (cadastro.getCliente() != null) {
            cadastroAtual.setCliente(cadastro.getCliente());
        }

        if (cadastro.getFuncionario() != null) {
            cadastroAtual.setFuncionario(cadastro.getFuncionario());
        }

        return cadastroRepository.save(cadastroAtual);
    }

    public MensagemDTO deleteCadastro(Integer idCadastro) {
        try {
            cadastroRepository.findById(idCadastro).orElseThrow();
            cadastroRepository.deleteById(idCadastro);
        } catch (Exception e) {
            return new MensagemDTO("ERRO", e.getMessage());
        }

        return new MensagemDTO("OK", "OK");
    }
}
