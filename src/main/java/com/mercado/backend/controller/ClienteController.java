package com.mercado.backend.controller;

import com.mercado.backend.model.Cliente;
import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping(value = "/cliente")
@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer idCliente) {
        return ResponseEntity.ok().body(clienteService.findById(idCliente));
    }

    @PostMapping
    public ResponseEntity<Cliente> insertCliente(@RequestBody Cliente cliente) {
        clienteService.insertCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping(value = "/{idCliente}")
    public ResponseEntity<Cliente> updateCliente(
        @PathVariable Integer idCliente, @RequestBody Cliente cliente
    ) {
        cliente.setIdCliente(idCliente);

        Cliente clienteAlterado = clienteService.updateCliente(cliente);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/cliente/{idCliente}")
            .buildAndExpand(cliente.getIdCliente()).toUri();

        return ResponseEntity.created(uri).body(clienteAlterado);
    }

    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<MensagemDTO> deleteCliente(
        @PathVariable Integer idCliente
    ) {
        MensagemDTO mensagemDTO = clienteService.deleteCliente(idCliente);
        return ResponseEntity.ok().body(mensagemDTO);
    }
}
