package com.api.repositories;

import com.api.model.entities.EntidadesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEntidadesRepository extends JpaRepository<EntidadesEntity, Long> {

    boolean existsByNitEntidad(String nitEntidad);
}
