/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.modelo;

/**
 * @author Jimena
 */
public class Usuario {

    private String nombre;
    private String carne;
    private String password;
    private int xp;
    private int nivel;

    public Usuario(String nombre, String carne, String password) {
        this.nombre = nombre;
        this.carne = carne;
        this.password = password;
        this.xp = 0;
        calcularNivel();
    }

    public void otorgarXP(int cantidad) {
        if (cantidad > 0) {
            xp += cantidad;
            calcularNivel();
        }
    }

    private void calcularNivel() {
        if (xp >= 7000) {
            nivel = 5;
        } else if (xp >= 3500) {
            nivel = 4;
        } else if (xp >= 1500) {
            nivel = 3;
        } else if (xp >= 500) {
            nivel = 2;
        } else {
            nivel = 1;
        }
    }

    public String getRango() {
        switch (nivel) {
            case 1: return "Aprendiz";
            case 2: return "Jugador";
            case 3: return "Veterano";
            case 4: return "Maestro";
            case 5: return "Leyenda";
            default: return "Sin rango";
        }
    }

    public int getXPParaSiguienteNivel() {
        switch (nivel) {
            case 1: return 500;
            case 2: return 1500;
            case 3: return 3500;
            case 4: return 7000;
            default: return 10000;
        }
    }

    public int getNivel() {
        return nivel;
    }

    public int getXP() {
        return xp;
    }

    public void setXP(int xp) {
        this.xp = xp;
        calcularNivel();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarne() {
        return carne;
    }

    public String getPassword() {
        return password;
    }
}
