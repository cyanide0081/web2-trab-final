package com.mercado.backend.service;

import com.mercado.backend.model.Funcionario;
import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.model.Produto;
import com.mercado.backend.model.Setor;
import com.mercado.backend.repository.FuncionarioRepository;
import com.mercado.backend.repository.ProdutoRepository;
import com.mercado.backend.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private SetorRepository setorRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Integer id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    public Produto insertProduto(Produto produto) {
        produto.setIdProduto(null);
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Produto produto) {
        Produto produtoAtual = produtoRepository
            .findById(produto.getIdProduto())
            .orElseThrow();

        if (produto.getNomeProduto() != null) {
            produtoAtual.setNomeProduto(produto.getNomeProduto());
        }

        if (produto.getSetores() != null) {
            produtoAtual.setSetores(produto.getSetores());
        }

        return produtoRepository.save(produtoAtual);
    }

    public MensagemDTO deleteProduto(Integer idProduto) {
        try {
            produtoRepository.findById(idProduto).orElseThrow();
            produtoRepository.deleteById(idProduto);
        } catch (Exception e) {
            return new MensagemDTO("ERRO", e.getMessage());
        }

        return new MensagemDTO("OK", "OK");
    }
}
