/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.modelo;

/**
 *
 * @author pichi
 */
public class Torneo {
    

    private String id;
    private String nombre;
    private String juego;
    private String fecha;
    private double precioTicket;
    private int ticketsDisponibles;

    public Torneo(String id, String nombre, String juego, String fecha, double precioTicket, int ticketsDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.juego = juego;
        this.fecha = fecha;
        this.precioTicket = precioTicket;
        this.ticketsDisponibles = ticketsDisponibles;
    }

    public boolean hayTickets() {
        return ticketsDisponibles > 0;
    }

    public synchronized void reducirTicket() {
        if (ticketsDisponibles > 0) {
            ticketsDisponibles--;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getJuego() {
        return juego;
    }

    public String getFecha() {
        return fecha;
    }

    public double getPrecioTicket() {
        return precioTicket;
    }

    public int getTicketsDisponibles() {
        return ticketsDisponibles;
    }
    
}
