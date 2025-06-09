package com.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class UsuariosEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String tipoDocumento;
    private String numDocumento;
    @Column(nullable = false, unique = true)
    private String email;
    private String telefono;

    @Column(updatable = false)
    private Timestamp fechaRegistro;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<AgendaEntity> agendas;
}
