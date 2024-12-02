package com.mercado.backend.controller;

import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.model.Setor;
import com.mercado.backend.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "/setor")
@RestController
public class SetorController {
    @Autowired
    private SetorService setorService;

    @GetMapping()
    public ResponseEntity<List<Setor>> findAll() {
        return ResponseEntity.ok().body(setorService.findAll());
    }

    @GetMapping(value = "/{idSetor}")
    public ResponseEntity<Setor> findById(@PathVariable Integer idSetor) {
        return ResponseEntity.ok().body(setorService.findById(idSetor));
    }

    @PostMapping
    public ResponseEntity<Setor> insertSetor(@RequestBody Setor produto) {
        setorService.insertSetor(produto);
        return ResponseEntity.ok().body(produto);
    }

    @PutMapping(value = "/{idSetor}")
    public ResponseEntity<Setor> updateSetor(
        @PathVariable Integer idSetor, @RequestBody Setor setor
    ) {
        setor.setIdSetor(idSetor);

        Setor setorAlterado = setorService.updateSetor(setor);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/setor/{idSetor}")
            .buildAndExpand(setor.getIdSetor()).toUri();

        return ResponseEntity.created(uri).body(setorAlterado);
    }

    @DeleteMapping(value = "/{idSetor}")
    public ResponseEntity<MensagemDTO> deleteSetor(
        @PathVariable Integer idSetor
    ) {
        MensagemDTO mensagemDTO = setorService.deleteSetor(idSetor);
        return ResponseEntity.ok().body(mensagemDTO);
    }
}
