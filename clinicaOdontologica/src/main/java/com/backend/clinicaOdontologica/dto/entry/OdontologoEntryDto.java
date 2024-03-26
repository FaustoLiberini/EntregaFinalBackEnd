package com.backend.clinicaOdontologica.dto.entry;

import javax.validation.constraints.*;

public class OdontologoEntryDto {

    @NotNull(message = "El nombre del odontologo no puede ser null")
    @NotBlank(message = "Especifique el nombre del odontologo")
    @Size(min = 2, max = 30, message = "El nombre no puede superar los 30 caracteres")
    private String nombre;
    @NotNull(message = "El apellido del odontologo no puede ser null")
    @NotBlank(message = "Especifique el apellido del odontologo")
    @Size(min = 2, max = 30, message = "El apellido no puede superar los 30 caracteres")
    private String apellido;
    @Positive(message = "La matricula del odontologo no puede ser null ni menor a 0")
    @Digits(integer = 12, fraction = 0, message = "La matricula debe tener como máximo 12 dígitos")
    private int matricula;


    public OdontologoEntryDto() {
    }

    public OdontologoEntryDto(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
