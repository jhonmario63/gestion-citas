package com.api.repositories;

import com.api.model.entities.CitasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface ICitasRepository extends JpaRepository<CitasEntity, Long> {
    List<CitasEntity> findByAgenda_FechaAgenda(Date fecha);
}
