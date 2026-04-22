/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.modelo;

/**
 *
 * @author Jimena Pichiya
 */
public class Participante {

    private String nombre;
    private String torneo;
    private String timestamp;

    public Participante(String nombre, String torneo, String timestamp) {
        this.nombre = nombre;
        this.torneo = torneo;
        this.timestamp = timestamp;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTorneo() {
        return torneo;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
