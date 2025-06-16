package com.api.repositories;

import com.api.model.entities.AgendaEntity;
import com.api.model.entities.CitasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface IAgendaRepository extends JpaRepository<AgendaEntity, Long> {
    List<AgendaEntity> findByFechaAgendaBetween(Timestamp fechaInicial, Timestamp fechaFinal);
}
