package com.backend.clinicaOdontologica.service;

import com.backend.clinicaOdontologica.dto.entry.OdontologoEntryDto;
import com.backend.clinicaOdontologica.dto.exit.OdontologoExitDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {

    OdontologoExitDto registrarOdontologo(OdontologoEntryDto odontologo);

    List<OdontologoExitDto> listarOdontologo();

    OdontologoExitDto buscarOdontologoPorId(Long id);

    void eliminarOrdontologoPorId(Long id) throws ResourceNotFoundException;

    OdontologoExitDto modificarOdontologo(OdontologoEntryDto odontologoEntryDto, Long id);
}
