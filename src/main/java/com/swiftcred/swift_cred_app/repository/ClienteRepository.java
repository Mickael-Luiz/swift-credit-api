package com.swiftcred.swift_cred_app.repository;

import com.swiftcred.swift_cred_app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
