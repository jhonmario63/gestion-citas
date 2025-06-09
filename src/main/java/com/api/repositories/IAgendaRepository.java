package com.api.repositories;

import com.api.model.CitasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgendaRepository extends JpaRepository<CitasEntity, Long> {
}
