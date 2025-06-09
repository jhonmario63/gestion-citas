package com.api.repositories;

import com.api.model.EntidadesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEntidadesRepository extends JpaRepository<EntidadesEntity, Long> {
}
