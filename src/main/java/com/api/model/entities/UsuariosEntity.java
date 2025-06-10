package com.api.model.entities;


import com.api.model.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Data;

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
    private String password;
    private String telefono;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<AgendaEntity> agendas;
    @Column(updatable = false)
    private Timestamp fechaRegistro;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuarioEnum tipoUsuario;

}
