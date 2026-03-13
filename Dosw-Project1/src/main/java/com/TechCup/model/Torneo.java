package com.techcup.model;

import java.time.LocalDate;

public class Torneo {
    private Long id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int cantidadEquipos;
    private double costoPorEquipo;
    private String estado; // BORRADOR, ACTIVO, EN_PROGRESO, FINALIZADO

    public Torneo() { this.estado = "BORRADOR"; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public int getCantidadEquipos() { return cantidadEquipos; }
    public void setCantidadEquipos(int cantidadEquipos) { this.cantidadEquipos = cantidadEquipos; }
    public double getCostoPorEquipo() { return costoPorEquipo; }
    public void setCostoPorEquipo(double costoPorEquipo) { this.costoPorEquipo = costoPorEquipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}