package com.api.repositories;

import com.api.model.CitasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitasRepository  extends JpaRepository<CitasEntity, Long> {
}
