package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entry.DomicilioEntryDto;
import com.backend.clinicaOdontologica.dto.entry.OdontologoEntryDto;
import com.backend.clinicaOdontologica.dto.entry.PacienteEntryDto;
import com.backend.clinicaOdontologica.dto.entry.TurnoEntryDto;
import com.backend.clinicaOdontologica.dto.exit.OdontologoExitDto;
import com.backend.clinicaOdontologica.dto.exit.PacienteExitDto;
import com.backend.clinicaOdontologica.dto.exit.TurnoExitDto;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;



    @Test
    @Order(1)
    void debeRegistrarUnTurnoCorrectamente_YRetornarunId() throws BadRequestException {

        PacienteEntryDto pacienteEntryDto = new PacienteEntryDto("Aswath", "Damodaran", 16343122, LocalDate.of(2024, 3, 28), new DomicilioEntryDto("Junin", 123, "San Martin", "Lobos"));
        PacienteExitDto pacienteExitDto = pacienteService.registrarPaciente(pacienteEntryDto);
        assertNotNull(pacienteExitDto);
        assertNotNull(pacienteExitDto.getId());


        OdontologoEntryDto odontologoEntryDto = new OdontologoEntryDto("Messi", "Poen", 3412563);
        OdontologoExitDto odontologoExitDto = odontologoService.registrarOdontologo(odontologoEntryDto);
        assertNotNull(odontologoExitDto);
        assertNotNull(odontologoExitDto.getId());


        TurnoEntryDto turnoEntryDto = new TurnoEntryDto(pacienteExitDto.getId(),odontologoExitDto.getId(), LocalDateTime.of(2024, 3, 28, 14, 30, 45));


        TurnoExitDto turnoExitDto;

        turnoExitDto = turnoService.registrarTurno(turnoEntryDto);


        assertNotNull(turnoExitDto);
        assertNotNull(turnoExitDto.getId());
    }

    @Test
    @Order(2)
    void debeDevolverUnaListaCon1Turno(){
        List<TurnoExitDto> listaTurnos = turnoService.listarTurnos();

        assertEquals(1, listaTurnos.size());
    }


    @Test
    @Order(2)
    void deberiaEliminarElTurnoConId1(){
        assertDoesNotThrow(() -> turnoService.eliminarTurnoPorId(1L));
    }







}
