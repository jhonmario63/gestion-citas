package com.api.model.entities;

import com.api.model.entities.AgendaEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "citas")
public class CitasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private AgendaEntity agenda;

    @Column(name = "fecha_hora")
    private Timestamp fechaHora;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
}
