package com.backend.clinicaOdontologica.controller;

import com.backend.clinicaOdontologica.dto.entry.PacienteEntryDto;
import com.backend.clinicaOdontologica.dto.exit.PacienteExitDto;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {

        this.pacienteService = pacienteService;
    }


    //  Los GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteExitDto> buscarPacientePorId(@PathVariable Long id) {
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PacienteExitDto>> listarTodosLosPacientes() {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    // POST

    @PostMapping("/registrar")
    public ResponseEntity<PacienteExitDto> registrarPaciente(@RequestBody @Valid PacienteEntryDto paciente) {
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    // PUT

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PacienteExitDto> actualizarPaciente(@RequestBody @Valid PacienteEntryDto paciente, @PathVariable Long id) {
        return new ResponseEntity<>(pacienteService.modificarPaciente(paciente, id), HttpStatus.OK);
    }

    // Delete

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPacientePorId(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}
