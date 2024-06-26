package com.backend.clinicaOdontologica.dto.exit;

import java.time.LocalDate;

public class PacienteExitDto {

    private Long id;
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaIngreso;

    private DomicilioExitDto domicilioExitDto;

    public PacienteExitDto() {
    }

    public PacienteExitDto(Long id, String nombre, String apellido, int dni, LocalDate fechaIngreso, DomicilioExitDto domicilioExitDto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioExitDto = domicilioExitDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DomicilioExitDto getDomicilioExitDto() {
        return domicilioExitDto;
    }

    public void setDomicilioExitDto(DomicilioExitDto domicilioExitDto) {
        this.domicilioExitDto = domicilioExitDto;
    }
}
