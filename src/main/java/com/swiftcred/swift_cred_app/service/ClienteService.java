package com.swiftcred.swift_cred_app.service;

import com.swiftcred.swift_cred_app.dtos.ClienteDTO;
import com.swiftcred.swift_cred_app.entity.Cliente;
import com.swiftcred.swift_cred_app.mappers.ClienteMapper;
import com.swiftcred.swift_cred_app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;

    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Page<ClienteDTO> listarTodosDTOPaginado(String filtro, Pageable pageable) {
        if(filtro == null || filtro.isBlank()) {
            return clienteRepository.findAll(pageable).map(clienteMapper::toDTO);
        } else {
            return  clienteRepository.findByFiltro(filtro, pageable).map(clienteMapper::toDTO);
        }
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com código "+id+" não encontrado"));
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public String deletarCliente(Long id) {
        try {
            var cliente = clienteRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Cliente não Encontrado", 1));
            clienteRepository.deleteById(id);
            return cliente.getNome();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
