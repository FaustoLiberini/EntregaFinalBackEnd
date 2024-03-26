package com.backend.clinicaOdontologica.dto.entry;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class PacienteEntryDto {
    @NotNull(message = "El nombre del paciente no puede ser null")
    @NotBlank(message = "Especifique el nombre del paciente")
    @Size(min = 2, max = 30, message = "El nombre del paciente no puede superar los 30 caracteres")
    private String nombre;
    @NotNull(message = "El apellido del paciente no puede ser null")
    @NotBlank(message = "Especifique el apellido del paciente")
    @Size(min = 2, max = 30, message = "El apellido del paciente no puede superar los 30 caracteres")
    private String apellido;

    @Positive(message = "EL DNI el paciente no puede ser null ni menor a 0")
    @Digits(integer = 9, fraction = 0, message = "El DNI del paciente no puede superar los 9 caracteres")
    private int dni;
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;
    @NotNull(message = "El domicilio del paciente no puede ser null")
    @Valid
    private DomicilioEntryDto domicilioEntryDto;

    public PacienteEntryDto() {
    }

    public PacienteEntryDto(String nombre, String apellido, int dni, LocalDate fechaIngreso, DomicilioEntryDto domicilioEntryDto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioEntryDto = domicilioEntryDto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioEntryDto getDomicilioEntryDto() {
        return domicilioEntryDto;
    }

    public void setDomicilioEntryDto(DomicilioEntryDto domicilioEntryDto) {
        this.domicilioEntryDto = domicilioEntryDto;
    }
}
