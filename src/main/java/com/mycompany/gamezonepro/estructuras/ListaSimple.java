/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.estructuras;

/**
 * @author Jimena Pichiyá 
 */
public class ListaSimple {

    private NodoSimple frente;
    private int tamanio;

    public ListaSimple() {
        frente = null;
        tamanio = 0;
    }

    public void insertar(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);

        if (frente == null) {
            frente = nuevo;
        } else {
            NodoSimple aux = frente;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }

        tamanio++;
    }

    public void insertarAlInicio(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        nuevo.siguiente = frente;
        frente = nuevo;
        tamanio++;
    }

    public void eliminar(Object dato) {
        if (frente == null) return;

        if (frente.dato.equals(dato)) {
            frente = frente.siguiente;
            tamanio--;
            return;
        }

        NodoSimple aux = frente;

        while (aux.siguiente != null) {
            if (aux.siguiente.dato.equals(dato)) {
                aux.siguiente = aux.siguiente.siguiente;
                tamanio--;
                return;
            }
            aux = aux.siguiente;
        }
    }

    public Object buscar(Object criterio) {
        NodoSimple aux = frente;

        while (aux != null) {
            if (aux.dato.equals(criterio)) {
                return aux.dato;
            }
            aux = aux.siguiente;
        }

        return null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }
    
    public Object buscarPrimerDato(){
        if(frente == null){
            return null; 
        }
        return frente.dato; 
    }
    
    public NodoSimple getFrente(){
        return frente; 
    }
}
