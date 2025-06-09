package com.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class EntidadesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntidad;

    private String nombre;
    private String direccion;
    private String telefono;
    private String tipo;

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL)
    private List<AgendaEntity> agendas;
    private Timestamp fechaRegistro;

}
