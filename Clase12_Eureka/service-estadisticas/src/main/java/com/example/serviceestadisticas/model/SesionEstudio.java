package com.example.serviceestadisticas.model;

import java.time.LocalDate;

/**
 * DTO espejo del modelo expuesto por service-estudio.
 * Se usa solo para deserializar la respuesta que llega via Feign.
 */
public class SesionEstudio {

    private Long id;
    private String curso;
    private LocalDate fecha;
    private double horas;
    private String tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
