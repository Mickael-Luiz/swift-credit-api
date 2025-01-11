package com.swiftcred.swift_cred_app.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parcelas")
public class parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emrpestimo_id")
    private Emprestimo emprestimo;

    @Column(name = "numero_parcela")
    private Integer numeroParcela;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "valor_original")
    private BigDecimal valorOriginal;

    @Column(name = "valor_atual")
    private BigDecimal valorAtual;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "staus")
    private String status;

    @Column(name = "valor_faltante")
    private BigDecimal valorFaltante;
}
