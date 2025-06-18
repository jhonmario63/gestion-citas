package com.api.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "citas_usuarios")
public class CitasUsuariosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "citas_id")
    private CitasEntity cita;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuariosEntity usuarios;

    @Column(name = "id_reunion")
    private String idReunion;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

}
