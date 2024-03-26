package com.backend.clinicaOdontologica.service;


import com.backend.clinicaOdontologica.dto.entry.PacienteEntryDto;
import com.backend.clinicaOdontologica.dto.exit.PacienteExitDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    PacienteExitDto registrarPaciente(PacienteEntryDto paciente);

    List<PacienteExitDto> listarPacientes();

    PacienteExitDto buscarPacientePorId(Long id);

    void eliminarPacientePorId(Long id) throws ResourceNotFoundException;

    PacienteExitDto modificarPaciente(PacienteEntryDto pacienteEntryDto, Long id);
}
