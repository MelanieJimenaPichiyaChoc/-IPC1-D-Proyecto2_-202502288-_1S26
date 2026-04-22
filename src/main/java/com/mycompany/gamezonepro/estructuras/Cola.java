/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.estructuras;

/**
 * @author Jimena
 */
public class Cola {

    private NodoCola frente;
    private NodoCola fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public void encolar(Object dato) {
        NodoCola nuevo = new NodoCola(dato);

        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }

        tamanio++;
    }

    public Object desencolar() {
        if (frente == null) {
            return null;
        }

        Object dato = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        tamanio--;
        return dato;
    }

    public Object peek() {
        if (frente == null) {
            return null;
        }

        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }
}
