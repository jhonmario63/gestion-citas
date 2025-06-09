package com.api.services.service;

import com.api.dto.request.UsuarioRequestDto;
import com.api.dto.response.UsuarioResponseDto;
import com.api.model.UsuariosEntity;
import com.api.repositories.IUsuariosRepository;
import com.api.services.interfaces.IUsuariosService;
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
public class UsuariosService implements IUsuariosService {

    private final IUsuariosRepository iUsuariosRepository;

    @Override
    public UsuarioResponseDto registrarUsuario(UsuarioRequestDto usuarioRequestDto) throws CustomException {
        if (iUsuariosRepository.existsByEmail(usuarioRequestDto.getEmail())) {
            throw new CustomException(MensajesEnum.USUARIO_EXISTENTE, HttpStatus.BAD_REQUEST);
        }
        try {
            UsuariosEntity usuariosEntity = new UsuariosEntity();
            usuariosEntity.setNombre(usuarioRequestDto.getNombre());
            usuariosEntity.setTipoDocumento(usuarioRequestDto.getTipoDocumento());
            usuariosEntity.setNumDocumento(usuarioRequestDto.getNumDocumento());
            usuariosEntity.setEmail(usuarioRequestDto.getEmail());
            usuariosEntity.setTelefono(usuarioRequestDto.getTelefono());
            usuariosEntity.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            UsuariosEntity save = iUsuariosRepository.save(usuariosEntity);
            if (save.getIdUsuario() == null) {
                throw new CustomException(MensajesEnum.ERROR_REGISTRO_USUARIO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return UsuarioResponseDto.builder()
                    .idUsuario(save.getIdUsuario())
                    .nombre(save.getNombre())
                    .tipoDocumento(save.getTipoDocumento())
                    .numDocumento(save.getNumDocumento())
                    .email(save.getEmail())
                    .telefono(save.getTelefono())
                    .fechaRegistro(save.getFechaRegistro())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_USUARIO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UsuarioResponseDto actualizarUsuario(UsuarioRequestDto usuarioRequestDto) throws CustomException {
        try {
            UsuariosEntity usuariosEntity = iUsuariosRepository.findById(usuarioRequestDto.getIdUsuario())
                    .orElseThrow(() -> new CustomException(MensajesEnum.USUARIO_NO_EXISTENTE, HttpStatus.NOT_FOUND));
            // Validación opcional: evitar duplicación de email si cambia
            if (!usuariosEntity.getEmail().equals(usuarioRequestDto.getEmail())
                    && iUsuariosRepository.existsByEmail(usuarioRequestDto.getEmail())) {
                throw new CustomException(MensajesEnum.CORREO_EXISTENTE, HttpStatus.BAD_REQUEST);
            }
            usuariosEntity.setNombre(usuarioRequestDto.getNombre());
            usuariosEntity.setTipoDocumento(usuarioRequestDto.getTipoDocumento());
            usuariosEntity.setNumDocumento(usuarioRequestDto.getNumDocumento());
            usuariosEntity.setEmail(usuarioRequestDto.getEmail());
            usuariosEntity.setTelefono(usuarioRequestDto.getTelefono());
            return UsuarioResponseDto.builder()
                    .idUsuario(usuariosEntity.getIdUsuario())
                    .nombre(usuariosEntity.getNombre())
                    .tipoDocumento(usuariosEntity.getTipoDocumento())
                    .numDocumento(usuariosEntity.getNumDocumento())
                    .email(usuariosEntity.getEmail())
                    .telefono(usuariosEntity.getTelefono())
                    .fechaRegistro(usuariosEntity.getFechaRegistro())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_ACTUALIZAR_USUARIO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
