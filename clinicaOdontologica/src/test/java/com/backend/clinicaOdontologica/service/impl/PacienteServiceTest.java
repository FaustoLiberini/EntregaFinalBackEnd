package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entry.DomicilioEntryDto;
import com.backend.clinicaOdontologica.dto.entry.PacienteEntryDto;
import com.backend.clinicaOdontologica.dto.exit.PacienteExitDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void debeRegistrarseUnPacienteConNombreAswath_YRetornarSuId() {

        PacienteEntryDto pacienteEntryDto = new PacienteEntryDto("Aswath", "Damodaran", 16343122, LocalDate.of(2024, 3, 28), new DomicilioEntryDto("Junin", 123, "San Martin", "Lobos"));

        PacienteExitDto pacienteExitDto = pacienteService.registrarPaciente(pacienteEntryDto);

        assertNotNull(pacienteExitDto);
        assertEquals(1, pacienteExitDto.getId());
        assertEquals("Aswath", pacienteExitDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaEliminarElPacienteConId1() {
        assertDoesNotThrow(() -> pacienteService.eliminarPacientePorId(1L));
    }

    @Test
    @Order(3)
    void deberiaDevolverUnaListaVacia() {
        List<PacienteExitDto> listaPacientes = pacienteService.listarPacientes();


        assertTrue(listaPacientes.isEmpty());
    }
}
