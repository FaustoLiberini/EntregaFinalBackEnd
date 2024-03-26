package com.backend.clinicaOdontologica.service;

import com.backend.clinicaOdontologica.dto.entry.TurnoEntryDto;
import com.backend.clinicaOdontologica.dto.exit.TurnoExitDto;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoExitDto registrarTurno(TurnoEntryDto turno) throws BadRequestException, ResourceNotFoundException;

    List<TurnoExitDto> listarTurnos();

    TurnoExitDto buscarTurnoPorId(Long id);

    void eliminarTurnoPorId(Long id) throws ResourceNotFoundException;

    TurnoExitDto modificarTurno(TurnoEntryDto turnoEntryDto, Long id);

}
