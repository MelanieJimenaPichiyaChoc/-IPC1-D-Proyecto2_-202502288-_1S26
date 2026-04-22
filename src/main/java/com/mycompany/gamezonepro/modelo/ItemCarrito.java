/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.modelo;

/**
 * @author Jimena
 */
public class ItemCarrito {
    
    private Juego juego; 
    private int cantidad;  
    
    public ItemCarrito(Juego jeugo, int cantidad){
        this.juego = juego; 
        this.cantidad = cantidad; 
    }
    
    public double getSubtotal(){
        return juego.getPrecio() * cantidad; 
    }
    
    public void setCatnidad(int n){
        if(n > 0){
            cantidad = n; 
        }
    }
    
    public Juego getJuego(){
        return juego; 
    }
    
    public int getCantidad(){
        return cantidad; 
    }
    
}
