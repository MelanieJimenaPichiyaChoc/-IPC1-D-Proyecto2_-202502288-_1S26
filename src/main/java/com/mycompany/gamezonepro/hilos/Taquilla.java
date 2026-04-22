/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.hilos;

import com.mycompany.gamezonepro.estructuras.Cola;
/**
 *
 * @author pichi
 */
public class Taquilla extends Thread {

    private Cola cola;
    private int numero;
    private boolean activa;

    public Taquilla(Cola cola, int numero) {
        this.cola = cola;
        this.numero = numero;
        this.activa = true;
    }

    @Override
    public void run() {
        while (activa) {
            if (!cola.estaVacia()) {
                Object dato = cola.desencolar();

                if (dato != null) {
                    System.out.println("Taquilla " + numero + " atendio a: " + dato);
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("La taquilla " + numero + " fue interrumpida.");
            }
        }

        System.out.println("Taquilla " + numero + " detenida.");
    }

    public void detener() {
        activa = false;
    }
}
