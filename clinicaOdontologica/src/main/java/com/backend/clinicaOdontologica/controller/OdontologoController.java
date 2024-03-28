package com.backend.clinicaOdontologica.controller;

import com.backend.clinicaOdontologica.dto.entry.OdontologoEntryDto;
import com.backend.clinicaOdontologica.dto.exit.OdontologoExitDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")

public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {

        this.odontologoService = odontologoService;
    }

    //  Los GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoExitDto> buscarOdontologoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<OdontologoExitDto>> listarTodosLosOdontologos() {
        return new ResponseEntity<>(odontologoService.listarOdontologo(), HttpStatus.OK);
    }

    // POST
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoExitDto> registrarOdontologo(@RequestBody @Valid OdontologoEntryDto odontologo) {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OdontologoExitDto> actualizarOdontologo(@RequestBody @Valid OdontologoEntryDto odontologo, @PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.modificarOdontologo(odontologo, id), HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/eliminar/{id}")//localhost:8080/pacientes/eliminar?id=x
    public ResponseEntity<?> eliminarOdontologo(@RequestParam Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOrdontologoPorId(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}
