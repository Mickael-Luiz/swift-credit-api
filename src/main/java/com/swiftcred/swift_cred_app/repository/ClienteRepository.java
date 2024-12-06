package com.swiftcred.swift_cred_app.repository;

import com.swiftcred.swift_cred_app.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c " +
            "WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR CAST(c.id AS string) LIKE CONCAT('%', :filtro, '%')")
    Page<Cliente> findByFiltro(@Param("filtro") String filtro, Pageable pageable);
}
