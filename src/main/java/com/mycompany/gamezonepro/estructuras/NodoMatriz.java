/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.estructuras;

import com.mycompany.gamezonepro.modelo.Carta;
/**
 * @author Jimena
 */
public class NodoMatriz {
    
    public Carta dato; 
    public NodoMatriz arriba; 
    public NodoMatriz abajo; 
    public NodoMatriz izquierda; 
    public NodoMatriz derecha; 
    
    public NodoMatriz(Carta dato){
        this.dato = dato; 
        this.arriba = null; 
        this.abajo = null; 
        this.izquierda = null; 
        this.derecha = null; 
    }
   
    
}
