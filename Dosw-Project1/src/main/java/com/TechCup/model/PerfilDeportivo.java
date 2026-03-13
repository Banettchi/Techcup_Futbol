package com.techcup.model;

public class PerfilDeportivo {
    private Long id;
    private String posicion;
    private int numeroDorsal;
    private boolean disponible;
    private Usuario usuario;

    public PerfilDeportivo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }
    public int getNumeroDorsal() { return numeroDorsal; }
    public void setNumeroDorsal(int numeroDorsal) { this.numeroDorsal = numeroDorsal; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}