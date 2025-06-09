package com.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitasRequestDto implements Serializable {

    private float idTurno;
    private Timestamp fechaTurno;
    private String identificacion;
    private String tipoIdentificacion;
    private String razonSocial;
    private String email;
    private String celular;
    private String ciudadResidencia;
    private Integer idTipoPersona;

}
