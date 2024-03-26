package com.backend.clinicaOdontologica.dto.exit;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TurnoExitDto {

    private Long id;
    private OdontologoExitDto odontologoExitDto;
    private PacienteExitDto pacienteExitDto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaYHora;

    public TurnoExitDto() {
    }

    public TurnoExitDto(Long id, OdontologoExitDto odontologoExitDto, PacienteExitDto pacienteExitDto, LocalDateTime fechaYHora) {
        this.id = id;
        this.odontologoExitDto = odontologoExitDto;
        this.pacienteExitDto = pacienteExitDto;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OdontologoExitDto getOdontologoExitDto() {
        return odontologoExitDto;
    }

    public void setOdontologoExitDto(OdontologoExitDto odontologoExitDto) {
        this.odontologoExitDto = odontologoExitDto;
    }

    public PacienteExitDto getPacienteExitDto() {
        return pacienteExitDto;
    }

    public void setPacienteExitDto(PacienteExitDto pacienteExitDto) {
        this.pacienteExitDto = pacienteExitDto;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
