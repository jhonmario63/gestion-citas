package com.api.model.entities;

import com.api.model.enums.TipoAgendaEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "agenda")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda")
    private Long idAgenda;

    @Column(name = "nombre_agenda", nullable = false)
    private String nombreAgenda;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuariosEntity usuario;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private EntidadesEntity entidad;

    @Column(name = "fecha_agenda")
    private Date fechaAgenda;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_agenda", nullable = false)
    private TipoAgendaEnum tipoAgenda;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<CitasEntity> citas;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

}
