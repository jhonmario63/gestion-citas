package com.api.services.service;

import com.api.dto.AuthenticatedUser;
import com.api.dto.request.CitasRequestDto;
import com.api.dto.response.CitasResponseDto;
import com.api.mapper.CitasMapper;
import com.api.model.entities.AgendaEntity;
import com.api.model.entities.CitasEntity;
import com.api.model.entities.CitasUsuariosEntity;
import com.api.model.entities.UsuariosEntity;
import com.api.model.enums.EstadoCitaEnum;
import com.api.repositories.IAgendaRepository;
import com.api.repositories.ICitasRepository;
import com.api.repositories.ICitasUsuariosRepository;
import com.api.repositories.IUsuariosRepository;
import com.api.services.interfaces.ICitasService;
import com.api.utils.MensajesEnum;
import com.api.utils.Util;
import com.api.utils.exceptions.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CitasService implements ICitasService {

    private final ICitasRepository iCitasRepository;
    private final IAgendaRepository iAgendaRepository;
    private final IUsuariosRepository iUsuariosRepository;
    private final ICitasUsuariosRepository iCitasUsuariosRepository;
    private final CitasMapper citasMapper;
    private final EmailService emailService;
    private final Util util;

    @Override
    public void registrarCita(CitasRequestDto citasRequestDto) throws CustomException {
        try {
            AgendaEntity agendaEntity = iAgendaRepository.findById(citasRequestDto.getAgenda().getIdAgenda())
                    .orElseThrow(() -> new CustomException(MensajesEnum.AGENDA_NO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST));
            CitasEntity citasEntity = new CitasEntity();
            citasEntity.setAgenda(agendaEntity);
            citasEntity.setHora(citasRequestDto.getHora());
            citasEntity.setEstado(EstadoCitaEnum.DISPONIBLE);
            citasEntity.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            CitasEntity save = iCitasRepository.save(citasEntity);
            if (save.getIdCita() == null) {
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
    public List<CitasResponseDto> listarCitas(Date fecha) throws CustomException {
        try {
            List<CitasEntity> citasEntities = iCitasRepository.findByAgenda_FechaAgenda(fecha);
            if (citasEntities.isEmpty()) {
                throw new CustomException(MensajesEnum.CITAS_NO_EXISTENTE.getMsg(), HttpStatus.NOT_FOUND);
            }
            return citasEntities.stream()
                    .map(citasMapper::toDto)
                    .collect(Collectors.toList());
        } catch (CustomException e) {
            throw e;
        } catch (Exception ex) {
            log.error(MensajesEnum.ERROR_SERVIDOR + ex.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_ENTIDAD.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public void agendarCitaUsuario(Long idCita, AuthenticatedUser user) throws CustomException {
        try {
            UsuariosEntity usuariosEntity = obtenerUsuarioEntity(user.getIdUsuario());
            CitasEntity citasEntity = obtenerCitaEntity(idCita);
            CitasUsuariosEntity citaUsuariosEntity = new CitasUsuariosEntity();
            citaUsuariosEntity.setUsuarios(usuariosEntity);
            citaUsuariosEntity.setCita(citasEntity);
            citaUsuariosEntity.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            iCitasUsuariosRepository.save(citaUsuariosEntity);
            this.cambiarEstadoCita(citasEntity, EstadoCitaEnum.AGENDADA);
            String html = util.generarHtmlCita(usuariosEntity.getNombre(), citasEntity.getAgenda().getFechaAgenda().toString(), citasEntity.getHora().toString());
            emailService.enviarCorreoConfirmacionCita(usuariosEntity.getEmail(), MensajesEnum.ASUNTO_EMAIL.getMsg(), html);
        } catch (CustomException e) {
            throw e;
        } catch (Exception ex) {
            log.error(MensajesEnum.ERROR_SERVIDOR + ex.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_ENTIDAD.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void cambiarEstadoCita(CitasEntity citasEntity, EstadoCitaEnum estado) throws CustomException {
        citasEntity.setEstado(estado);
        iCitasRepository.save(citasEntity);
    }

    private CitasEntity obtenerCitaEntity(Long idCita) throws CustomException {
        return iCitasRepository.findById(idCita)
                .orElseThrow(() -> new CustomException(MensajesEnum.CITA_NO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST));
    }

    private UsuariosEntity obtenerUsuarioEntity(Long idUsuario) throws CustomException {
        return iUsuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new CustomException(MensajesEnum.USUARIO_NO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST));
    }

}
