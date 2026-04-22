/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.modelo;

/**
 * @author Jimena
 */
public class Logro {

    private String nombre;
    private String descripcion;
    private int xpRecompensa;
    private boolean desbloqueado;
    private String fechaDesbloqueo;

    public Logro(String nombre, String descripcion, int xpRecompensa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.xpRecompensa = xpRecompensa;
        this.desbloqueado = false;
        this.fechaDesbloqueo = "";
    }

    public boolean verificar(Usuario u) {
        if (!desbloqueado && u.getXP() >= xpRecompensa) {
            return true;
        }
        return false;
    }

    public void desbloquear(Usuario u) {
        if (verificar(u)) {
            desbloqueado = true;
            fechaDesbloqueo = java.time.LocalDate.now().toString();
            u.otorgarXP(xpRecompensa);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDesbloqueado() {
        return desbloqueado;
    }

    public String getFechaDesbloqueo() {
        return fechaDesbloqueo;
    }
}
