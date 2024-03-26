package com.backend.clinicaOdontologica.controller;

import com.backend.clinicaOdontologica.dto.entry.PacienteEntryDto;
import com.backend.clinicaOdontologica.dto.entry.TurnoEntryDto;
import com.backend.clinicaOdontologica.dto.exit.PacienteExitDto;
import com.backend.clinicaOdontologica.dto.exit.TurnoExitDto;
import com.backend.clinicaOdontologica.exceptions.BadRequestException;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //  Los GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoExitDto> buscarTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TurnoExitDto>> listarTodosLosTurnos() {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    // POST

    @PostMapping("/registrar")
    public ResponseEntity<TurnoExitDto> registrarTurno(@RequestBody @Valid TurnoEntryDto turno) throws BadRequestException, ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.registrarTurno(turno), HttpStatus.CREATED);
    }

    // PUT

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoExitDto> actualizarTurno(@RequestBody @Valid TurnoEntryDto turno, @PathVariable Long id) {
        return new ResponseEntity<>(turnoService.modificarTurno(turno, id), HttpStatus.OK);
    }

    // Delete

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@RequestParam Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurnoPorId(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }


}
