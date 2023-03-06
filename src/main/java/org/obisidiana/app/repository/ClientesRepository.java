package org.obisidiana.app.repository;

import org.obisidiana.app.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Long> {
    public Optional<Clientes> findByEmail(String email);
}
