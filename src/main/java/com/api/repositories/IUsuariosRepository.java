package com.api.repositories;

import com.api.model.entities.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuariosRepository extends JpaRepository<UsuariosEntity, Long> {
    boolean existsByEmail(String email);

    Optional<UsuariosEntity> findByEmail(String email);

    boolean existsByNumDocumento(String numDocumento);
}
