package com.api.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuariosEntity usuario;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private EntidadesEntity entidad;

    private Timestamp fecha;
    private String tipoCita;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<CitasEntity> citas;
    private Timestamp fechaRegistro;


}
