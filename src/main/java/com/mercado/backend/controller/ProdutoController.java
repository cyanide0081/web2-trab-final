package com.mercado.backend.controller;

import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.model.Produto;
import com.mercado.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "/produto")
@RestController
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok().body(produtoService.findAll());
    }

    @GetMapping(value = "/{idProduto}")
    public ResponseEntity<Produto> findById(
        @PathVariable Integer idProduto
    ) {
        return ResponseEntity.ok().body(produtoService.findById(idProduto));
    }

    @PostMapping
    public ResponseEntity<Produto> insertProduto(@RequestBody Produto produto) {
        produtoService.insertProduto(produto);
        return ResponseEntity.ok().body(produto);
    }

    @PutMapping(value = "/{idProduto}")
    public ResponseEntity<Produto> updateProduto(
        @PathVariable Integer idProduto, @RequestBody Produto produto
    ) {
        produto.setIdProduto(idProduto);

        Produto produtoAlterado = produtoService.updateProduto(produto);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/produto/{idProduto}")
            .buildAndExpand(produto.getIdProduto()).toUri();

        return ResponseEntity.created(uri).body(produtoAlterado);
    }

    @DeleteMapping(value = "/{idProduto}")
    public ResponseEntity<MensagemDTO> deleteProduto(
        @PathVariable Integer idProduto
    ) {
        MensagemDTO mensagemDTO = produtoService.deleteProduto(idProduto);
        return ResponseEntity.ok().body(mensagemDTO);
    }
}
