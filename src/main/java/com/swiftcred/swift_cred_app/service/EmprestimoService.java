package com.swiftcred.swift_cred_app.service;

import com.swiftcred.swift_cred_app.dtos.EmprestimoDTO;
import com.swiftcred.swift_cred_app.entity.Cliente;
import com.swiftcred.swift_cred_app.entity.Emprestimo;
import com.swiftcred.swift_cred_app.entity.Parcela;
import com.swiftcred.swift_cred_app.enums.StatusEmprestimo;
import com.swiftcred.swift_cred_app.enums.StatusParcela;
import com.swiftcred.swift_cred_app.repository.ClienteRepository;
import com.swiftcred.swift_cred_app.repository.EmprestimoRepository;
import com.swiftcred.swift_cred_app.repository.ParcelaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ParcelaRepository parcelaRepository;

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
            List<Emprestimo> emprestimos = emprestimoRepository.findAllByCliente_IdOrderByIdDesc(clienteId);
            emprestimos.forEach(emprestimo -> System.out.println(emprestimo.getId()));
            return emprestimos;
        } else {
            return this.emprestimoRepository.findAllOrderByIdDesc();
        }
    }

    public Emprestimo buscarPorId(Long emprestimoId) {
        return emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new NoSuchElementException("Empréstimo não encontrado"));
    }

    @Transactional
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

        List<Parcela> parcelas = criarParcelas(emprestimo);
        parcelaRepository.saveAll(parcelas);
    }

    public List<Parcela> criarParcelas(Emprestimo emprestimo) {
        List<Parcela> parcelas = new ArrayList<>();

        Integer totalParcelas = emprestimo.getTotalParcelas();
        BigDecimal valorTotal = emprestimo.getValorTotal();
        BigDecimal valorParcela = valorTotal.divide(BigDecimal.valueOf(totalParcelas), 2, RoundingMode.HALF_UP);

        LocalDate dataVencimento = emprestimo.getDataContratacao().plusMonths(1);

        for(int i=1; i <= totalParcelas; i++) {
            Parcela parcela = new Parcela();
            parcela.setEmprestimo(emprestimo);
            parcela.setNumeroParcela(i);
            parcela.setDataVencimento(dataVencimento);
            parcela.setValorOriginal(valorParcela);
            parcela.setValorAtual(valorParcela);
            parcela.setValorPago(BigDecimal.ZERO);
            parcela.setDataPagamento(null);
            parcela.setStatus(StatusParcela.PENDENTE);
            parcela.setValorFaltante(valorParcela);

            parcelas.add(parcela);
            dataVencimento = dataVencimento.plusMonths(1);
        }

        return parcelas;
    }


}
