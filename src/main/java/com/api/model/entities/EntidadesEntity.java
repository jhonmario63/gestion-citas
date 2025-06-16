package com.api.model.entities;

import com.api.model.entities.AgendaEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "entidades")
public class EntidadesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad")
    private Long idEntidad;

    @Column(name = "nit_entidad", nullable = false, unique = true)
    private String nitEntidad;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuariosEntity usuario;

}
