package com.backend.clinicaOdontologica.dto.entry;

import javax.validation.constraints.*;

public class DomicilioEntryDto {
    @NotNull(message = "La calle no puede ser null")
    @NotBlank(message = "Debe especificar la calle")
    @Size(min = 2, max = 50, message = "La calle no puede superar los 50 caracteres")
    private String calle;
    @Positive(message = "La altura de la direccion no puede ser null ni menor a 0")
    @Digits(integer = 6, fraction = 0, message = "El número debe tener como máximo 6 dígitos")
    private int numero;
    @NotNull(message = "La localidad no puede ser null")
    @NotBlank(message = "Debe especificar la localidad")
    @Size(min = 2, max = 50, message = "La localidad no puede superar los 50 caracteres")
    private String localidad;
    @NotNull(message = "La provincia no puede ser null")
    @NotBlank(message = "Debe especificar la provincia")
    @Size(min = 2, max = 50, message = "La provincia no puede superar los 50 caracteres")
    private String provincia;

    public DomicilioEntryDto() {
    }

    public DomicilioEntryDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
