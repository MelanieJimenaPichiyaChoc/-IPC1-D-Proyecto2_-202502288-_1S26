/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.hilos;


import com.mycompany.gamezonepro.estructuras.Cola;
import com.mycompany.gamezonepro.gui.EventosEspeciales;
import com.mycompany.gamezonepro.modelo.Participante;
import com.mycompany.gamezonepro.modelo.Torneo;
;
/**
 * @author Jimena
 */
public class Taquilla extends Thread {

    private Cola cola;
    private int numero;
    private boolean activa;
    private Torneo torneo;
    private EventosEspeciales ventana;

    public Taquilla(Cola cola, int numero, Torneo torneo, EventosEspeciales ventana) {
        this.cola = cola;
        this.numero = numero;
        this.torneo = torneo;
        this.ventana = ventana;
        this.activa = true;
    }

    @Override
    public void run() {
        while (activa) {
            try {
                Object obj = cola.desencolar();

                if (obj == null) {
                    sleep(300);
                    continue;
                }

                Participante participante = (Participante) obj;

                ventana.actualizarEstadoTaquilla(numero, "Procesando a: " + participante.getNombre());
                ventana.actualizarColaDesdeHilo();

                sleep(800 + (int) (Math.random() * 1200));

                synchronized (torneo) {
                    if (torneo.hayTickets()) {
                        torneo.reducirTicket();
                        ventana.registrarVenta(numero, participante, torneo);
                    } else {
                        ventana.registrarLog("T" + numero + ": Tickets agotados para " + torneo.getNombre());
                        break;
                    }
                }

            } catch (Exception e) {
                ventana.registrarLog("T" + numero + ": Error en proceso");
            }
        }

        ventana.actualizarEstadoTaquilla(numero, "Libre / finalizada");
    }

    public void detener() {
        activa = false;
    }
}
