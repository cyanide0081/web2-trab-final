package com.mercado.backend.controller;

import com.mercado.backend.model.Cadastro;
import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "/cadastro")
@RestController
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;

    @GetMapping()
    public ResponseEntity<List<Cadastro>> findAll() {
        return ResponseEntity.ok().body(cadastroService.findAll());
    }

    @GetMapping(value = "/{idCadastro}")
    public ResponseEntity<Cadastro> findById(@PathVariable Integer idCadastro) {
        return ResponseEntity.ok().body(cadastroService.findById(idCadastro));
    }

    @PostMapping
    public ResponseEntity<Cadastro> insertCadastro(
        @RequestBody Cadastro cadastro
    ) {
        cadastroService.insertCadastro(cadastro);
        return ResponseEntity.ok().body(cadastro);
    }

    @PutMapping(value = "/{idCadastro}")
    public ResponseEntity<Cadastro> updateCadastro(
        @PathVariable Integer idCadastro, @RequestBody Cadastro cadastro
    ) {
        cadastro.setIdCadastro(idCadastro);

        Cadastro cadastroAlterado = cadastroService.updateCadastro(cadastro);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/cadastro/{idCadastro}")
            .buildAndExpand(cadastro.getIdCadastro()).toUri();

        return ResponseEntity.created(uri).body(cadastroAlterado);
    }

    @DeleteMapping(value = "/{idCadastro}")
    public ResponseEntity<MensagemDTO> deleteCadastro(
        @PathVariable Integer idCadastro
    ) {
        MensagemDTO mensagemDTO = cadastroService.deleteCadastro(idCadastro);
        return ResponseEntity.ok().body(mensagemDTO);
    }
}
