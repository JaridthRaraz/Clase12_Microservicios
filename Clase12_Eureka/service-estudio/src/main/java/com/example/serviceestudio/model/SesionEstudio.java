package com.example.serviceestudio.model;

import java.time.LocalDate;

/**
 * Representa una sesion de estudio.
 * Campos IDENTICOS al modelo real del Lab 6 (repo Lab6_20267541_fixed,
 * clase StudySession.java), que en Firestore vive en users/{uid}/sesiones/{id}:
 *   curso: String
 *   fecha: Date/Timestamp
 *   horas: double
 *   tipo:  "Teoria" | "Practica" | "Laboratorio" (con tildes, ver Tipos.java)
 */
public class SesionEstudio {

    private Long id;
    private String curso;
    private LocalDate fecha;
    private double horas;
    private String tipo; // "Teoría" | "Práctica" | "Laboratorio"

    public SesionEstudio() {
    }

    public SesionEstudio(Long id, String curso, LocalDate fecha, double horas, String tipo) {
        this.id = id;
        this.curso = curso;
        this.fecha = fecha;
        this.horas = horas;
        this.tipo = tipo;
    }

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
