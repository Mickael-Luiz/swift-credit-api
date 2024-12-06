package com.swiftcred.swift_cred_app.dtos;

import java.math.BigDecimal;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String confiabilidade;
    private BigDecimal debito;

    public ClienteDTO(Long id, String nome, String telefone, String confiabilidade, BigDecimal debito, Long recomendanteId) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.confiabilidade = confiabilidade;
        this.debito = debito;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getConfiabilidade() {
        return confiabilidade;
    }

    public void setConfiabilidade(String confiabilidade) {
        this.confiabilidade = confiabilidade;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }
}
