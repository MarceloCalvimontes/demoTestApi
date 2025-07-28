package com.kernotec.test.repository;

import com.kernotec.test.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByHabilitado(boolean habilitado);
}