package com.api.services.service;

import com.api.config.security.JwtUtil;
import com.api.dto.request.LoginRequestDto;
import com.api.dto.request.UsuarioRequestDto;
import com.api.dto.response.UsuarioResponseDto;
import com.api.mapper.UsuarioMapper;
import com.api.model.entities.UsuariosEntity;
import com.api.model.enums.TipoUsuarioEnum;
import com.api.repositories.IUsuariosRepository;
import com.api.services.interfaces.IUsuariosService;
import com.api.utils.MensajesEnum;
import com.api.utils.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsuariosService implements IUsuariosService {

    private final IUsuariosRepository iUsuariosRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    @Override
    public void registrarUsuario(UsuarioRequestDto usuarioRequestDto) throws CustomException {
        if (iUsuariosRepository.existsByEmail(usuarioRequestDto.getEmail())) {
            throw new CustomException(MensajesEnum.USUARIO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
        }
        if (iUsuariosRepository.existsByNumDocumento(usuarioRequestDto.getNumDocumento())) {
            throw new CustomException(MensajesEnum.DOCUMENTO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
        }
        try {
            UsuariosEntity usuariosEntity = new UsuariosEntity();
            usuariosEntity.setNombre(usuarioRequestDto.getNombre());
            usuariosEntity.setTipoDocumento(usuarioRequestDto.getTipoDocumento());
            usuariosEntity.setNumDocumento(usuarioRequestDto.getNumDocumento());
            usuariosEntity.setEmail(usuarioRequestDto.getEmail());
            usuariosEntity.setTelefono(usuarioRequestDto.getTelefono());
            usuariosEntity.setPassword(passwordEncoder.encode(usuarioRequestDto.getPassword()));
            usuariosEntity.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            usuariosEntity.setTipoUsuario(usuarioRequestDto.getTipoUsuario() != null ? usuarioRequestDto.getTipoUsuario() : TipoUsuarioEnum.USER);
            UsuariosEntity save = iUsuariosRepository.save(usuariosEntity);
            if (save.getIdUsuario() == null) {
                throw new CustomException(MensajesEnum.ERROR_REGISTRO_USUARIO.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error(MensajesEnum.ERROR_SERVIDOR + e.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_USUARIO.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void actualizarUsuario(UsuarioRequestDto usuarioRequestDto) throws CustomException {
        try {
            UsuariosEntity usuariosEntity = iUsuariosRepository.findById(usuarioRequestDto.getIdUsuario())
                    .orElseThrow(() -> new CustomException(MensajesEnum.USUARIO_NO_EXISTENTE.getMsg(), HttpStatus.NOT_FOUND));
            if (!usuariosEntity.getEmail().equals(usuarioRequestDto.getEmail())
                    && iUsuariosRepository.existsByEmail(usuarioRequestDto.getEmail())) {
                throw new CustomException(MensajesEnum.CORREO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
            }
            usuariosEntity.setNombre(usuarioRequestDto.getNombre());
            usuariosEntity.setTipoDocumento(usuarioRequestDto.getTipoDocumento());
            usuariosEntity.setNumDocumento(usuarioRequestDto.getNumDocumento());
            usuariosEntity.setEmail(usuarioRequestDto.getEmail());
            usuariosEntity.setTelefono(usuarioRequestDto.getTelefono());
            iUsuariosRepository.save(usuariosEntity);
        } catch (CustomException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(MensajesEnum.ERROR_SERVIDOR.getMsg() + ex.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_SERVIDOR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UsuarioResponseDto login(LoginRequestDto loginRequestDto) throws CustomException {
        try {
            UsuariosEntity usuariosEntity = iUsuariosRepository.findByEmail(loginRequestDto.getEmail())
                    .orElseThrow(() -> new CustomException(MensajesEnum.USUARIO_INCORRECTO.getMsg(), HttpStatus.UNAUTHORIZED));
            if (!passwordEncoder.matches(loginRequestDto.getPassword(), usuariosEntity.getPassword())) {
                throw new CustomException(MensajesEnum.USUARIO_INCORRECTO.getMsg(), HttpStatus.UNAUTHORIZED);
            }
            String token = JwtUtil.generateToken(usuariosEntity.getEmail());
            UsuarioResponseDto usuarioResponseDto = usuarioMapper.toDto(usuariosEntity);
            usuarioResponseDto.setToken(token);
            return usuarioResponseDto;
        } catch (CustomException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(MensajesEnum.ERROR_SERVIDOR.getMsg() + ex.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_SERVIDOR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
