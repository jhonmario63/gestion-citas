package com.api.repositories;

import com.api.model.entities.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgendaRepository extends JpaRepository<AgendaEntity, Long> {
}
