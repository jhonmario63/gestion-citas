package com.api.services.service;

import com.api.dto.request.AgendaRequestDto;
import com.api.model.entities.AgendaEntity;
import com.api.repositories.IAgendaRepository;
import com.api.repositories.IEntidadesRepository;
import com.api.repositories.IUsuariosRepository;
import com.api.services.interfaces.IAgendaService;
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
public class AgendaService implements IAgendaService {

    private final IAgendaRepository iAgendaRepository;
    private final IUsuariosRepository iUsuariosRepository;
    private final IEntidadesRepository iEntidadesRepository;

    @Override
    public void registrarAgenda(AgendaRequestDto agendaRequestDto) throws CustomException {
        try {
            Long idUsuario = agendaRequestDto.getUsuario().getIdUsuario();
            Long idEntidad = agendaRequestDto.getEntidad().getIdEntidad();
            if (idUsuario == null || !iUsuariosRepository.existsById(idUsuario)) {
                throw new CustomException(MensajesEnum.USUARIO_NO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
            }
            if (idEntidad == null || !iEntidadesRepository.existsById(idEntidad)) {
                throw new CustomException(MensajesEnum.ENTIDAD_NO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
            }
            AgendaEntity agendaEntity = new AgendaEntity();
            agendaEntity.setFecha(agendaRequestDto.getFecha());
            agendaEntity.setTipoCita(agendaRequestDto.getTipoCita());
            agendaEntity.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            agendaEntity.setUsuario(iUsuariosRepository.getReferenceById(idUsuario));
            agendaEntity.setEntidad(iEntidadesRepository.getReferenceById(idEntidad));
            iAgendaRepository.save(agendaEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            log.error(MensajesEnum.ERROR_SERVIDOR + e.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_AGENDA.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
