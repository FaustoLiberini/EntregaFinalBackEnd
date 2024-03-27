package com.backend.clinicaOdontologica.service.impl;

import com.backend.clinicaOdontologica.dto.entry.OdontologoEntryDto;
import com.backend.clinicaOdontologica.dto.exit.OdontologoExitDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void debeRegistrarUnOdontologoConNombreMessi_yRetornarSuId() {

        OdontologoEntryDto odontologoEntryDto = new OdontologoEntryDto("Messi", "Poen", 3412563);

        OdontologoExitDto odontologoExitDto = odontologoService.registrarOdontologo(odontologoEntryDto);


        assertNotNull(odontologoExitDto);
        assertEquals("Messi", odontologoExitDto.getNombre());

    }

    @Test
    @Order(2)
    void debieraEliminarElOdontologoId1() {
        assertDoesNotThrow(() -> odontologoService.eliminarOrdontologoPorId(1L));
    }

    @Test
    @Order(3)
    void debieraDevolverUnaListaVacia() {
        List<OdontologoExitDto> listaOdontologo = odontologoService.listarOdontologo();

        assertTrue(listaOdontologo.isEmpty());
    }


}
