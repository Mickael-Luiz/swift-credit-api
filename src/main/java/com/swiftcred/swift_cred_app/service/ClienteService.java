package com.swiftcred.swift_cred_app.service;

import com.swiftcred.swift_cred_app.dtos.ClienteDTO;
import com.swiftcred.swift_cred_app.entity.Cliente;
import com.swiftcred.swift_cred_app.mappers.ClienteMapper;
import com.swiftcred.swift_cred_app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<ClienteDTO> listarTodosDTO() {
        return clienteRepository.findAll().stream().map(clienteMapper::toDTO).collect(Collectors.toList());
    }

    public Page<ClienteDTO> listarTodosDTOPaginado(String filtro, Pageable pageable) {
        if(filtro == null || filtro.isBlank()) {
            return clienteRepository.findAll(pageable).map(clienteMapper::toDTO);
        } else {
            return  clienteRepository.findByFiltro(filtro, pageable).map(clienteMapper::toDTO);
        }
    }
}
