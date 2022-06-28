package com.example.proyetoClinicaOdontologica.domain;

import java.time.LocalDate;

public class Paciente {
    private Long id;
    private String apellido;
    private String nombre;
    private String email;
    private String DNI;
    private LocalDate fechaIngreso;
    private Domicilio domicilio;
    private Odontologo odontologo;

    //Constructores


    public Paciente() {
    }

    public Paciente(Long id, String apellido, String nombre, String email, String DNI, LocalDate fechaIngreso, Domicilio domicilio, Odontologo odontologo) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.DNI = DNI;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.odontologo = odontologo;
    }

    public Paciente(String apellido, String nombre, String email, String DNI, LocalDate fechaIngreso, Domicilio domicilio, Odontologo odontologo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.DNI = DNI;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.odontologo = odontologo;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }
}
