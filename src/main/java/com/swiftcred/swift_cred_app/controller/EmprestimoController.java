package com.swiftcred.swift_cred_app.controller;

import com.swiftcred.swift_cred_app.dtos.EmprestimoDTO;
import com.swiftcred.swift_cred_app.entity.Emprestimo;
import com.swiftcred.swift_cred_app.service.EmprestimoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/paginado")
    public Page<Emprestimo> buscarEmprestimosPaginado(
            Pageable pageable,
            @RequestParam(value = "clienteId", required = false) Long clienteId
    ) {
        return emprestimoService.buscarEmprestimosPaginado(clienteId, pageable);
    }

    @GetMapping
    public List<Emprestimo> buscarListaEmprestimos(
            @RequestParam(value = "clienteId", required = false) Long clienteId
    ) {
        return this.emprestimoService.listarEmprestimos(clienteId);
    }

    @GetMapping("/{id}")
    public Emprestimo buscarPorId(@PathVariable Long id) {
        return emprestimoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<String> salvarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        emprestimoService.salvarEmprestimo(emprestimoDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
