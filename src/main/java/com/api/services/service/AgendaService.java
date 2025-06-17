package com.api.services.service;

import com.api.dto.AuthenticatedUser;
import com.api.dto.request.AgendaRequestDto;
import com.api.dto.response.AgendaResponseDto;
import com.api.mapper.AgendaMapper;
import com.api.model.entities.AgendaEntity;
import com.api.model.entities.EntidadesEntity;
import com.api.model.entities.UsuariosEntity;
import com.api.repositories.IAgendaRepository;
import com.api.repositories.IEntidadesRepository;
import com.api.repositories.IUsuariosRepository;
import com.api.services.interfaces.IAgendaService;
import com.api.utils.MensajesEnum;
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
public class AgendaService implements IAgendaService {

    private final IAgendaRepository iAgendaRepository;
    private final IUsuariosRepository iUsuariosRepository;
    private final IEntidadesRepository iEntidadesRepository;
    private final AgendaMapper agendaMapper;

    @Transactional
    @Override
    public void registrarAgenda(AgendaRequestDto agendaRequestDto, AuthenticatedUser user) throws CustomException {
        try {
            if (iAgendaRepository.existsByNombreAgendaAndFechaAgendaAndTipoAgenda(
                    agendaRequestDto.getNombreAgenda(),
                    agendaRequestDto.getFechaAgenda(),
                    agendaRequestDto.getTipoAgenda())) {
                throw new CustomException(MensajesEnum.AGENDA_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
            }
            UsuariosEntity usuario = iUsuariosRepository.findById(user.getIdUsuario())
                    .orElseThrow(() -> new CustomException(MensajesEnum.USUARIO_NO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST));
            EntidadesEntity entidad = iEntidadesRepository.findById(agendaRequestDto.getEntidad().getIdEntidad())
                    .orElseThrow(() -> new CustomException(MensajesEnum.ENTIDAD_NO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST));
            AgendaEntity agendaEntity = new AgendaEntity();
            agendaEntity.setNombreAgenda(agendaRequestDto.getNombreAgenda());
            agendaEntity.setEntidad(entidad);
            agendaEntity.setTipoAgenda(agendaRequestDto.getTipoAgenda());
            agendaEntity.setFechaAgenda(agendaRequestDto.getFechaAgenda());
            agendaEntity.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            agendaEntity.setUsuario(usuario);
            iAgendaRepository.save(agendaEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error(MensajesEnum.ERROR_SERVIDOR + e.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_AGENDA.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public List<AgendaResponseDto> listarAgendas(Date fecha, AuthenticatedUser user) throws CustomException {
        try {
            List<AgendaEntity> agenda = iAgendaRepository.findByFechaAgenda(fecha);
            if (agenda.isEmpty()) {
                throw new CustomException(MensajesEnum.AGENDA_LIST_NO_EXISTENTE.getMsg(), HttpStatus.NOT_FOUND);
            }
            return agenda.stream()
                    .map(agendaMapper::toDto)
                    .collect(Collectors.toList());
        } catch (CustomException e) {
            throw e;
        } catch (Exception ex) {
            log.error(MensajesEnum.ERROR_SERVIDOR + ex.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_ENTIDAD.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
