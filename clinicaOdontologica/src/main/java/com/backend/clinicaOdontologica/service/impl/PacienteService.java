package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entry.PacienteEntryDto;
import com.backend.clinicaOdontologica.dto.exit.PacienteExitDto;
import com.backend.clinicaOdontologica.entity.Paciente;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.repository.PacienteRepository;
import com.backend.clinicaOdontologica.service.IPacienteService;
import com.backend.clinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private ModelMapper modelMapper;
    private PacienteRepository pacienteRepository;


    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {

        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }


    @Override
    public PacienteExitDto registrarPaciente(PacienteEntryDto paciente) {

        Paciente pacienteEntiedad = modelMapper.map(paciente, Paciente.class);
        Paciente pacienteConId = pacienteRepository.save(pacienteEntiedad);
        PacienteExitDto pacienteExitDto = modelMapper.map(pacienteConId, PacienteExitDto.class);
        LOGGER.info("PacienteExitDto: {}", JsonPrinter.toString(pacienteExitDto));
        return pacienteExitDto;
    }

    @Override
    public List<PacienteExitDto> listarPacientes() {

        List<PacienteExitDto> listaPacientesExitDto = pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteExitDto.class))
                .toList();

        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(listaPacientesExitDto));
        return listaPacientesExitDto;
    }

    @Override
    public PacienteExitDto buscarPacientePorId(Long id) {

        Paciente pacineteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteExitDto pacienteEncontrado = null;

        if (pacineteBuscado != null) {
            pacienteEncontrado = modelMapper.map(pacineteBuscado, PacienteExitDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));

        } else LOGGER.error("No se encontro un paciente con ese ID en la base de datos");

        return pacienteEncontrado;
    }

    @Override
    public void eliminarPacientePorId(Long id) throws ResourceNotFoundException {
        if (buscarPacientePorId(id) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se elimino el paciente con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe un registro del paciente con id: " + id);
        }
    }

    @Override
    public PacienteExitDto modificarPaciente(PacienteEntryDto pacienteEntryDto, Long id) {
        Paciente pacienteActualizado = pacienteRepository.findById(id).orElse(null);
        PacienteExitDto pacienteExitDto = null;

        if (pacienteActualizado != null) {

            pacienteActualizado.setNombre(pacienteEntryDto.getNombre());
            pacienteActualizado.setApellido(pacienteEntryDto.getApellido());

            pacienteActualizado = pacienteRepository.save(pacienteActualizado);

            pacienteExitDto = modelMapper.map(pacienteActualizado, PacienteExitDto.class);

            LOGGER.warn("Paciente actualizado: {}", pacienteExitDto);
        } else {
            LOGGER.error("Error al actualizar el paciente: no se encuentra en la BD");
        }
        return pacienteExitDto;
    }


    private void configureMapping() {
        modelMapper.typeMap(PacienteEntryDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntryDto::getDomicilioEntryDto, Paciente::setDomicilio));

        modelMapper.typeMap(Paciente.class, PacienteExitDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteExitDto::setDomicilioExitDto));
    }
}
