package com.api.model.entities;

import com.api.model.entities.AgendaEntity;
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
    @Column(nullable = false, unique = true)
    private String nitEntidad;
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipo;

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL)
    private List<AgendaEntity> agendas;
    private Timestamp fechaRegistro;


}
