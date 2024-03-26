package com.backend.clinicaOdontologica.entity;

import javax.persistence.*;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ODONTOLOGO_ID")
    private Long id;
    @Column(length = 30)
    private String nombre;
    @Column(length = 30)
    private String apellido;
    @Column(length = 12)
    private int matricula;

    public Odontologo() {
    }

    public Odontologo(Long id, String nombre, String apellido, int matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }


    @Override
    public String toString() {
        return "ID: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - Matricula: " + matricula;
    }

}
