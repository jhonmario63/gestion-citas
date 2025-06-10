package com.api.services.service;

import com.api.dto.request.EntidadRequestDto;
import com.api.model.entities.EntidadesEntity;
import com.api.repositories.IEntidadesRepository;
import com.api.services.interfaces.IEntidadesService;
import com.api.utils.MensajesEnum;
import com.api.utils.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntidadesService implements IEntidadesService {

    private final IEntidadesRepository iEntidadesRepository;

    @Override
    public void registrarEntidad(EntidadRequestDto entidadRequestDto) throws CustomException {
        try {
            if (iEntidadesRepository.existsByNitEntidad(entidadRequestDto.getNitEntidad())) {
                throw new CustomException(MensajesEnum.USUARIO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
            }
            EntidadesEntity entidadesEntity = new EntidadesEntity();
            entidadesEntity.setNitEntidad(entidadRequestDto.getNitEntidad());
            entidadesEntity.setNombre(entidadRequestDto.getNombre());
            entidadesEntity.setDireccion(entidadRequestDto.getDireccion());
            entidadesEntity.setTelefono(entidadRequestDto.getTelefono());
            entidadesEntity.setTipo(entidadRequestDto.getTipo());
            entidadesEntity.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            EntidadesEntity save = iEntidadesRepository.save(entidadesEntity);
            if (save.getNitEntidad() == null) {
                throw new CustomException(MensajesEnum.ERROR_REGISTRO_ENTIDAD.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error(MensajesEnum.ERROR_SERVIDOR + e.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_ENTIDAD.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void actualizarEntidad(EntidadRequestDto entidadRequestDto) throws CustomException {
        try {
            EntidadesEntity entidadesEntity = iEntidadesRepository.findById(entidadRequestDto.getIdEntidad())
                    .orElseThrow(() -> new CustomException(MensajesEnum.USUARIO_NO_EXISTENTE.getMsg(), HttpStatus.NOT_FOUND));
            // Validación opcional: evitar duplicación de nit entidad si cambia
            if (!entidadesEntity.getNitEntidad().equals(entidadRequestDto.getNitEntidad())
                    && iEntidadesRepository.existsByNitEntidad(entidadRequestDto.getNitEntidad())) {
                throw new CustomException(MensajesEnum.CORREO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
            }
            entidadesEntity.setNitEntidad(entidadRequestDto.getNitEntidad());
            entidadesEntity.setNombre(entidadRequestDto.getNombre());
            entidadesEntity.setDireccion(entidadRequestDto.getDireccion());
            entidadesEntity.setTelefono(entidadRequestDto.getTelefono());
            entidadesEntity.setTipo(entidadRequestDto.getTipo());
            entidadesEntity.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            iEntidadesRepository.save(entidadesEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error(MensajesEnum.ERROR_SERVIDOR + e.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_ACTUALIZAR_USUARIO.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
