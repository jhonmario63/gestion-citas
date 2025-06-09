package com.api.repositories;

import com.api.model.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosRepository extends JpaRepository<UsuariosEntity, Long> {
    boolean existsByEmail(String email);
}
