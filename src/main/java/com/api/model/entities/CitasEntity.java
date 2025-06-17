package com.api.model.entities;

import com.api.model.enums.EstadoCitaEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
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

    @Column(name = "hora")
    private Time hora;

    @Column(name = "estado")
    private EstadoCitaEnum estado;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
}
