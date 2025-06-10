package com.api.model.entities;

import com.api.model.entities.AgendaEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class CitasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private AgendaEntity agenda;
    private Timestamp fechaHora;
    private String estado;
    private Timestamp fechaRegistro;
}
