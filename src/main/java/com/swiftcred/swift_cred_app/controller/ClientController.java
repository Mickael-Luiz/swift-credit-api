package com.swiftcred.swift_cred_app.controller;

import com.swiftcred.swift_cred_app.dtos.ClienteDTO;
import com.swiftcred.swift_cred_app.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClienteService clienteService;

    public ClientController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> listarTodosDTO() {
        return clienteService.listarTodosDTO();
    }

    @GetMapping("/paginado")
    public Page<ClienteDTO> listarTodosDTOPaginado(
            Pageable pageable,
            @RequestParam(value = "filtro", required = false) String filtro
    ) {
        return clienteService.listarTodosDTOPaginado(filtro, pageable);
    }

}
