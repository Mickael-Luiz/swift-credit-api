package com.swiftcred.swift_cred_app.dtos;

import java.math.BigDecimal;

public class EmprestimoDTO {

    private Long id;

    private Long clienteId;

    private BigDecimal valorEmprestado;

    private BigDecimal valorTotal;

    private Integer totalParcelas;

    private BigDecimal taxaJuros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getValorEmprestado() {
        return valorEmprestado;
    }

    public void setValorEmprestado(BigDecimal valorEmprestado) {
        this.valorEmprestado = valorEmprestado;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(Integer totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public BigDecimal getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }
}
