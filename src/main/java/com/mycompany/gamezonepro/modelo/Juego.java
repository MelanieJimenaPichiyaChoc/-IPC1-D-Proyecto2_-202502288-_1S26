/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.modelo;

/**
 * @author Jimena
 */
public class Juego {
    
    private String codigo; 
    private String nombre; 
    private String genero; 
    private String plataforma; 
    private double precio; 
    private int stock; 
    private String descripcion;
    
    public Juego(String codigo, String nombre, String genero, String plataforma, double precio, int stock, String descripcion){
        this.codigo = codigo; 
        this.nombre = nombre; 
        this.genero = genero; 
        this.plataforma = plataforma; 
        this.precio = precio; 
        this.stock = stock; 
        this.descripcion = descripcion; 
    }
    
    public String getCodigo(){
        return codigo; 
    }
    
    public double getPrecio(){
        return precio; 
    }
    
    public void reducirStock(int cantidad){
        if(cantidad > 0 && stock >= cantidad){
            stock -= cantidad; 
        }
    }
    
    public String getGenero(){
        return genero; 
    }
    
    public String getPlataforma(){
        return plataforma; 
    }
    
    public int getStock(){
        return stock; 
    }
    
    public String getDescripcion(){
        return descripcion; 
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre; 
    }
    
    public void setGenero(String genero){
        this.genero = genero; 
    }
    
    public void setPlataforma(String plataforma){
        this.plataforma = plataforma; 
    }
    
    public void setPrecio(double precio){
        this.precio = precio; 
    }
    
    public void setStock(int stock){
        this.stock = stock; 
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion; 
    }
}
