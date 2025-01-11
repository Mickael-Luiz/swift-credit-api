package com.swiftcred.swift_cred_app.repository;

import com.swiftcred.swift_cred_app.entity.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT em FROM Emprestimo em " +
            "WHERE em.cliente.id = :clienteId")
    public Page<Emprestimo> findAllByFiltro(@Param("clienteId") Long clienteId, Pageable pageable);

    public List<Emprestimo> findAllByCliente_Id(Long clienteId);
}
