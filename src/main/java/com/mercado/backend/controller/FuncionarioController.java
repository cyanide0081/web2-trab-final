package com.mercado.backend.controller;

import com.mercado.backend.model.Funcionario;
import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "/funcionario")
@RestController
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping()
    public ResponseEntity<List<Funcionario>> findAll() {
        return ResponseEntity.ok().body(funcionarioService.findAll());
    }

    @GetMapping(value = "/{idFuncionario}")
    public ResponseEntity<Funcionario> findById(
        @PathVariable Integer idFuncionario
    ) {
        return ResponseEntity.ok().body(
            funcionarioService.findById(idFuncionario)
        );
    }

    @PostMapping
    public ResponseEntity<Funcionario> insertFuncionario(
        @RequestBody Funcionario funcionario
    ) {
        funcionarioService.insertFuncionario(funcionario);
        return ResponseEntity.ok().body(funcionario);
    }

    @PutMapping(value = "/{idFuncionario}")
    public ResponseEntity<Funcionario> updateFuncionario(
        @PathVariable Integer idFuncionario,
        @RequestBody Funcionario funcionario
    ) {
        funcionario.setIdFuncionario(idFuncionario);

        Funcionario funcionarioAlterado = funcionarioService
            .updateFuncionario(funcionario);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/funcionario/{idFuncionario}")
            .buildAndExpand(funcionario.getIdFuncionario()).toUri();

        return ResponseEntity.created(uri).body(funcionarioAlterado);
    }

    @DeleteMapping(value = "/{idFuncionario}")
    public ResponseEntity<MensagemDTO> deleteFuncionario(
        @PathVariable Integer idFuncionario
    ) {
        MensagemDTO mensagemDTO = funcionarioService
            .deleteFuncionario(idFuncionario);
        return ResponseEntity.ok().body(mensagemDTO);
    }
}
