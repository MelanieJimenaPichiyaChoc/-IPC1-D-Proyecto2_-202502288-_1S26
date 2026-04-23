/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.modelo;

/**
 * @author Jimena
 */
public class Carta {
    
    private String codigo; 
    private String nombre; 
    private String tipo; 
    private String rareza; 
    private String imagen; 
    private int ataque; 
    private int defensa; 
    private int ps; 
    
    public Carta(String codigo, String nombre, String tipo, String rareza, int ataque, int defensa, int ps) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rareza = rareza;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ps = ps;
        this.imagen = "";
    }

    public Carta(String codigo, String nombre, String tipo, String rareza, int ataque, int defensa, int ps, String imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rareza = rareza;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ps = ps;
        this.imagen = imagen;
    }
    
    public String getCodigo(){
        return codigo; 
    }
    
    public boolean esLegendaria(){
        return rareza.equalsIgnoreCase("legendaria");
    }
    
    public String getNombre(){
        return nombre; 
    }
    
    public String getTipo(){
        return tipo; 
    }
    
    public String getRareza(){
        return rareza; 
    }
    
    public int getAtaque(){
        return ataque; 
    }
    
    public int getDefensa(){
        return defensa; 
    }
    
    public int getPs(){
        return ps; 
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre; 
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo; 
    }
    
    public void setRareza(String rareza){
        this.rareza = rareza; 
    }
    
    public void setAtaque(int ataque){
        this.ataque = ataque; 
    }
    
    public void setDefensa(int defensa){
        this.defensa = defensa; 
    }
    
    public void setPs(int ps){
        this.ps = ps; 
    }
    
    public String getImagen() {
        return imagen;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
