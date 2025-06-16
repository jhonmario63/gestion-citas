package com.api.services.service;

import com.api.dto.request.CitasRequestDto;
import com.api.dto.response.CitasResponseDto;
import com.api.mapper.AgendaMapper;
import com.api.model.entities.CitasEntity;
import com.api.repositories.IAgendaRepository;
import com.api.repositories.ICitasRepository;
import com.api.services.interfaces.ICitasService;
import com.api.utils.MensajesEnum;
import com.api.utils.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CitasService implements ICitasService {

    private final ICitasRepository iCitasRepository;
    private final IAgendaRepository iAgendaRepository;
    private final AgendaMapper agendaMapper;

    @Override
    public void registrarCita(CitasRequestDto citasRequestDto) throws CustomException {
        try {
            Long idAgenda = citasRequestDto.getAgenda().getIdAgenda();
            if (idAgenda == null || !iAgendaRepository.existsById(idAgenda)) {
                throw new CustomException(MensajesEnum.AGENDA_NO_EXISTENTE.getMsg(), HttpStatus.BAD_REQUEST);
            }
            CitasEntity citasEntity = new CitasEntity();
            citasEntity.setAgenda(iAgendaRepository.getReferenceById(citasRequestDto.getAgenda().getIdAgenda()));
            citasEntity.setFechaHora(citasRequestDto.getFechaHora());
            citasEntity.setEstado(citasRequestDto.getEstado());
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
    public List<CitasResponseDto> listarCitas(Timestamp fechaInicial, Timestamp fechaFinal) throws CustomException {
        try {
            List<CitasEntity> citas = iCitasRepository.findByFechaHoraBetween(fechaInicial, fechaFinal);
            if (citas.isEmpty()) {
                throw new CustomException(MensajesEnum.CITAS_NO_EXISTENTE.getMsg(), HttpStatus.NOT_FOUND);
            }
            return citas.stream()
                    .map(cita -> CitasResponseDto.builder()
                            .idCita(cita.getIdCita())
                            .fechaHora(cita.getFechaHora())
                            .estado(cita.getEstado())
                            .fechaRegistro(cita.getFechaRegistro())
                            .agenda(agendaMapper.toDto(cita.getAgenda()))
                            .build())
                    .collect(Collectors.toList());
        } catch (CustomException e) {
            throw e;
        } catch (Exception ex) {
            log.error(MensajesEnum.ERROR_SERVIDOR + ex.getMessage(), this.getClass().getName());
            throw new CustomException(MensajesEnum.ERROR_REGISTRO_ENTIDAD.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
