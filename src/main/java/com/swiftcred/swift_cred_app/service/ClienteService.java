package com.swiftcred.swift_cred_app.service;

import com.swiftcred.swift_cred_app.dtos.ClienteDTO;
import com.swiftcred.swift_cred_app.entity.Cliente;
import com.swiftcred.swift_cred_app.mappers.ClienteMapper;
import com.swiftcred.swift_cred_app.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;

    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }
    public List<ClienteDTO> listarTodosDTO() {
        return clienteRepository.findAll().stream().map(clienteMapper::toDTO).collect(Collectors.toList());
    }

    public Page<ClienteDTO> listarTodosDTOPaginado(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(clienteMapper::toDTO);
    }
}
