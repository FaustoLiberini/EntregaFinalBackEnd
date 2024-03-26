package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entry.TurnoEntryDto;
import com.backend.clinicaOdontologica.dto.exit.OdontologoExitDto;
import com.backend.clinicaOdontologica.dto.exit.PacienteExitDto;
import com.backend.clinicaOdontologica.dto.exit.TurnoExitDto;
import com.backend.clinicaOdontologica.entity.Odontologo;
import com.backend.clinicaOdontologica.entity.Paciente;
import com.backend.clinicaOdontologica.entity.Turno;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.repository.TurnoRepository;
import com.backend.clinicaOdontologica.service.ITurnoService;
import com.backend.clinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    private TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;

        configurarAmbiguity();

    }

    @Override
    public TurnoExitDto registrarTurno(TurnoEntryDto turnoEntryDto) throws BadRequestException {
        TurnoExitDto turnoExitDto;
        PacienteExitDto paciente = pacienteService.buscarPacientePorId(turnoEntryDto.getPacienteId());
        OdontologoExitDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntryDto.getOdontologoId());

        String odontologoNoBD = "El odontologo no se encuentra en la base de datos";
        String pacienteNoBD = "El paciente no se encuentra en la base de datos";
        String ambosNoBD = "El paciente y el odontologo no se encuentran en la base de datos";

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.error(ambosNoBD);
                throw new BadRequestException(ambosNoBD);
            } else if (paciente == null) {
                LOGGER.error(pacienteNoBD);
                throw new BadRequestException(pacienteNoBD);
            } else {
                LOGGER.error(odontologoNoBD);
                throw new BadRequestException(odontologoNoBD);
            }
        } else {
            Turno nuevoTurno = turnoRepository.save(modelMapper.map(turnoEntryDto, Turno.class));
            turnoExitDto = entidadDtoExit(nuevoTurno, paciente, odontologo);
            LOGGER.info("Nuevo turno registrado: {}", JsonPrinter.toString(turnoExitDto));
        }

        return turnoExitDto;
    }

    @Override
    public List<TurnoExitDto> listarTurnos() {
        List<TurnoExitDto> listaTurnosExitDto = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoExitDto.class))
                .toList();

        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(listaTurnosExitDto));
        return listaTurnosExitDto;
    }

    @Override
    public TurnoExitDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoExitDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoExitDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));

        } else LOGGER.error("No se encontro un paciente con ese ID en la base de datos");

        return turnoEncontrado;

    }

    @Override
    public void eliminarTurnoPorId(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se elimino el turno con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe un registro del turno con id: " + id);
        }

    }

    @Override
    public TurnoExitDto modificarTurno(TurnoEntryDto turnoEntryDto, Long id) {

        Turno turnoActualizado = turnoRepository.findById(id).orElse(null);
        TurnoExitDto turnoExitDto = null;

        if (turnoActualizado != null) {

            turnoActualizado.setFechaYHora(turnoEntryDto.getFechaYHora());

            turnoActualizado =turnoRepository.save(turnoActualizado);

           turnoExitDto = modelMapper.map(turnoActualizado, TurnoExitDto.class);

            LOGGER.warn("Turno actualizado: {}", turnoExitDto);
        } else {
            LOGGER.error("Error al actualizar el turno: no se encuentra en la BD");
        }

        return turnoExitDto;
    }


    private void configurarAmbiguity() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    private TurnoExitDto entidadDtoExit(Turno turno, PacienteExitDto pacienteExitDto, OdontologoExitDto odontologoExitDto) {


        TurnoExitDto turnoExitDto = modelMapper.map(turno, TurnoExitDto.class);
        turnoExitDto.setPacienteExitDto(pacienteExitDto);
        turnoExitDto.setOdontologoExitDto(odontologoExitDto);


        return turnoExitDto;
    }



}
