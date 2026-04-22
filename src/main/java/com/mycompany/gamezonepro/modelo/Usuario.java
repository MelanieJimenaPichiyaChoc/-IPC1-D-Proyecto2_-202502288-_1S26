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
    private int xp; 
    private int nivel; 
    
    public Usuario(String nombre, String carne){
        this.nombre = nombre; 
        this.carne = carne; 
        this.xp = xp; 
        this.nivel = 1; 
    }
    
    public void otorgarXP(int cantidad){
        if(cantidad > 0){
            xp += cantidad;
            
            while(xp >= nivel * 100){
                xp -= nivel * 100; 
                subirNivel(); 
            }
        }
    }
    
    public void subirNivel(){
        nivel++; 
    }
    
    public int getNivel(){
        return nivel; 
    }
    
    public int getXP(){
        return xp; 
    }
    
    public String getNombre(){
        return nombre; 
    }
    
    public String getCarne(){
        return carne; 
    }
    
    public void setCarne(String carne){
        this.carne = carne; 
    }
    
}
