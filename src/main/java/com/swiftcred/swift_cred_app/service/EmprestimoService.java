package com.swiftcred.swift_cred_app.service;

import com.swiftcred.swift_cred_app.dtos.EmprestimoDTO;
import com.swiftcred.swift_cred_app.entity.Cliente;
import com.swiftcred.swift_cred_app.entity.Emprestimo;
import com.swiftcred.swift_cred_app.enums.StatusEmprestimo;
import com.swiftcred.swift_cred_app.repository.ClienteRepository;
import com.swiftcred.swift_cred_app.repository.EmprestimoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private ClienteRepository clienteRepository;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            ClienteRepository clienteRepository
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Page<Emprestimo> buscarEmprestimosPaginado(Long clienteId, Pageable pageable) {
        if(clienteId == null) {
            return emprestimoRepository.findAll(pageable);
        } else {
            return emprestimoRepository.findAllByFiltro(clienteId, pageable);
        }
    }

    public List<Emprestimo> listarEmprestimos(Long clienteId) {
        if(clienteId != null) {
            return this.emprestimoRepository.findAllByCliente_Id(clienteId);
        } else {
            return this.emprestimoRepository.findAll();
        }
    }

    public Emprestimo buscarPorId(Long emprestimoId) {
        return emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new NoSuchElementException("Empréstimo não encontrado"));
    }

    public void salvarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Cliente cliente = clienteRepository.findById(emprestimoDTO.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setValorEmprestado(emprestimoDTO.getValorEmprestado());
        emprestimo.setValorTotal(emprestimoDTO.getValorTotal());
        emprestimo.setTotalParcelas(emprestimoDTO.getTotalParcelas());
        emprestimo.setTaxaJuros(emprestimoDTO.getTaxaJuros());
        emprestimo.setSaldoDevedor(emprestimoDTO.getValorTotal());

        emprestimo.setStatus(StatusEmprestimo.PENDENTE);
        emprestimo.setParcelasPagas(0);
        emprestimo.setDataContratacao(LocalDate.now());
        emprestimoRepository.save(emprestimo);
    }


}
