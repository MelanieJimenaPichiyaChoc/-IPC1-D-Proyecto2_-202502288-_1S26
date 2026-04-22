/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.modelo;

import com.mycompany.gamezonepro.estructuras.ListaSimple;

/**
 * @author Jimena
 */
public class Compra {
    
    private String id; 
    private String fecha; 
    private ListaSimple items;
    private double total; 
    
    

    public Compra(String id, String fecha) {
        this.id = id;
        this.fecha = fecha;
        this.items = new ListaSimple();
        this.total = 0;
    }

    public void agregarItem(ItemCarrito item) {
        if (item != null) {
            items.insertar(item);
            total += item.getSubtotal();
        }
    }

    public double getTotal() {
        return total;
    }

    public String getResumen() {
        String resumen = "Compra: " + id + "\n";
        resumen += "Fecha: " + fecha + "\n";
        resumen += "Items:\n";

        ListaSimple temporal = new ListaSimple();

        while (!items.estaVacia()) {
            Object obj = extraerPrimero(items);

            if (obj != null) {
                temporal.insertar(obj);

                ItemCarrito item = (ItemCarrito) obj;
                resumen += "- " + item.getJuego().getNombre()
                        + " | Cantidad: " + item.getCantidad()
                        + " | Subtotal: Q" + item.getSubtotal() + "\n";
            }
        }

        while (!temporal.estaVacia()) {
            Object obj = extraerPrimero(temporal);
            if (obj != null) {
                items.insertar(obj);
            }
        }

        resumen += "Total: Q" + total;

        return resumen;
    }

    private Object extraerPrimero(ListaSimple lista) {
        if (lista.estaVacia()) {
            return null;
        }

        Object primero = lista.buscarPrimerDato();
        if (primero != null) {
            lista.eliminar(primero);
        }
        return primero;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public ListaSimple getItems() {
        return items;
    }
    
}
