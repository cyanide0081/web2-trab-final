package com.mercado.backend.service;

import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.model.Setor;
import com.mercado.backend.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;

    public List<Setor> findAll() {
        return setorRepository.findAll();
    }

    public Setor findById(Integer id) {
        return setorRepository.findById(id).orElseThrow();
    }

    public Setor insertSetor(Setor setor) {
        setor.setIdSetor(null);
        return setorRepository.save(setor);
    }

    public Setor updateSetor(Setor setor) {
        Setor setorAtual = setorRepository
            .findById(setor.getIdSetor())
            .orElseThrow();

        if (setor.getNomeSetor() != null) {
            setorAtual.setNomeSetor(setor.getNomeSetor());
        }

        if (setor.getProdutos() != null) {
            setorAtual.setProdutos(setor.getProdutos());
        }

        return setorRepository.save(setorAtual);
    }

    public MensagemDTO deleteSetor(Integer idSetor) {
        try {
            setorRepository.findById(idSetor).orElseThrow();
            setorRepository.deleteById(idSetor);
        } catch (Exception e) {
            return new MensagemDTO("ERRO", e.getMessage());
        }

        return new MensagemDTO("OK", "OK");
    }
}
