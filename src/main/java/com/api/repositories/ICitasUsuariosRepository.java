package com.api.repositories;

import com.api.model.entities.CitasUsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitasUsuariosRepository extends JpaRepository<CitasUsuariosEntity, Long> {
}
