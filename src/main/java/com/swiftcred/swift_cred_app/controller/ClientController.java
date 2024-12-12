package com.swiftcred.swift_cred_app.controller;

import com.swiftcred.swift_cred_app.dtos.ClienteDTO;
import com.swiftcred.swift_cred_app.entity.Cliente;
import com.swiftcred.swift_cred_app.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClienteService clienteService;

    public ClientController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/paginado")
    public Page<ClienteDTO> listarTodosDTOPaginado(
            Pageable pageable,
            @RequestParam(value = "filtro", required = false) String filtro
    ) {
        return clienteService.listarTodosDTOPaginado(filtro, pageable);
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping()
    public Cliente salvarCliente(@RequestBody Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }

    @PutMapping
    public Cliente atualizarCliente(@RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
