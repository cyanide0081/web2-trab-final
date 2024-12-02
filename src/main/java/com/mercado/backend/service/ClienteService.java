package com.mercado.backend.service;

import com.mercado.backend.model.Cliente;
import com.mercado.backend.model.MensagemDTO;
import com.mercado.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    public Cliente insertCliente(Cliente cliente) {
        cliente.setIdCliente(null);
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Cliente cliente) {
        Cliente clienteAtual = clienteRepository
            .findById(cliente.getIdCliente())
            .orElseThrow();

        if (cliente.getCadastroCliente() != null) {
            clienteAtual.setCadastroCliente(cliente.getCadastroCliente());
        }

        return clienteRepository.save(clienteAtual);
    }

    public MensagemDTO deleteCliente(Integer idCliente) {
        try {
            clienteRepository.findById(idCliente).orElseThrow();
            clienteRepository.deleteById(idCliente);
        } catch (Exception e) {
            return new MensagemDTO("ERRO", e.getMessage());
        }

        return new MensagemDTO("OK", "OK");
    }
}
