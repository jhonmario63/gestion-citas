package com.api.repositories;

import com.api.model.entities.AgendaEntity;
import com.api.model.enums.TipoAgendaEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface IAgendaRepository extends JpaRepository<AgendaEntity, Long> {
    List<AgendaEntity> findByFechaAgenda(Date fecha);

    boolean existsByNombreAgendaAndFechaAgendaAndTipoAgenda(String nombreAgenda, Date fechaAgenda, TipoAgendaEnum tipoAgenda);
}
