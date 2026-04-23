/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.estructuras;

import com.mycompany.gamezonepro.modelo.Carta;

/**
 * @author Jimena
 */
public class MallaOrtogonal {

    private NodoMatriz origen;
    private int filas;
    private int columnas;

    public MallaOrtogonal() {
        origen = null;
    }

    public void construir(int filas, int cols) {
        this.filas = filas;
        this.columnas = cols;

        NodoMatriz[][] matriz = new NodoMatriz[filas][cols];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                matriz[i][j] = new NodoMatriz(null);
            }
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                if (i > 0) {
                    matriz[i][j].arriba = matriz[i - 1][j];
                }
                if (i < filas - 1) {
                    matriz[i][j].abajo = matriz[i + 1][j];
                }
                if (j > 0) {
                    matriz[i][j].izquierda = matriz[i][j - 1];
                }
                if (j < cols - 1) {
                    matriz[i][j].derecha = matriz[i][j + 1];
                }
            }
        }

        origen = matriz[0][0];
    }

    public boolean insertar(Carta c) {
        NodoMatriz vacio = getPrimerVacio();
        if (vacio != null) {
            vacio.dato = c;
            return true;
        }
        return false;
    }

    public NodoMatriz getPrimerVacio() {
        NodoMatriz fila = origen;

        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                if (actual.dato == null) {
                    return actual;
                }
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }

        return null;
    }

    public NodoMatriz[] buscar(String criterio) {
        ListaSimple encontrados = new ListaSimple();

        NodoMatriz fila = origen;
        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                if (actual.dato != null) {
                    Carta c = actual.dato;
                    if (c.getNombre().toLowerCase().contains(criterio.toLowerCase())
                            || c.getTipo().toLowerCase().contains(criterio.toLowerCase())
                            || c.getRareza().toLowerCase().contains(criterio.toLowerCase())
                            || c.getCodigo().toLowerCase().contains(criterio.toLowerCase())) {
                        encontrados.insertar(actual);
                    }
                }
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }

        int n = encontrados.getTamanio();
        NodoMatriz[] resultado = new NodoMatriz[n];
        NodoSimple aux = encontrados.getFrente();
        int i = 0;

        while (aux != null) {
            resultado[i] = (NodoMatriz) aux.dato;
            aux = aux.siguiente;
            i++;
        }

        return resultado;
    }

    public void intercambiar(int f1, int c1, int f2, int c2) {
        NodoMatriz n1 = getNodo(f1, c1);
        NodoMatriz n2 = getNodo(f2, c2);

        if (n1 != null && n2 != null) {
            Carta temp = n1.dato;
            n1.dato = n2.dato;
            n2.dato = temp;
        }
    }

    public NodoMatriz[] recorrer() {
        ListaSimple lista = new ListaSimple();

        NodoMatriz fila = origen;
        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                lista.insertar(actual);
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }

        NodoMatriz[] arreglo = new NodoMatriz[lista.getTamanio()];
        NodoSimple aux = lista.getFrente();
        int i = 0;

        while (aux != null) {
            arreglo[i] = (NodoMatriz) aux.dato;
            aux = aux.siguiente;
            i++;
        }

        return arreglo;
    }

    public NodoMatriz getNodo(int fila, int col) {
        NodoMatriz actual = origen;

        for (int i = 0; i < fila && actual != null; i++) {
            actual = actual.abajo;
        }

        for (int j = 0; j < col && actual != null; j++) {
            actual = actual.derecha;
        }

        return actual;
    }
    
    public void quitar(int fila, int col) {
        NodoMatriz nodo = getNodo(fila, col);
        if (nodo != null) {
            nodo.dato = null;
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}