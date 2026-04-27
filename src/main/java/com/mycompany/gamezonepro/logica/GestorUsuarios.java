/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamezonepro.logica;

import com.mycompany.gamezonepro.modelo.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author pichi
 */
public class GestorUsuarios {
    
    public static void guardarXPUsuario(Usuario usuarioActual) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String contenido = "";
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length == 4 && partes[1].equals(usuarioActual.getCarne())) {
                    contenido += usuarioActual.getNombre() + "|"
                            + usuarioActual.getCarne() + "|"
                            + usuarioActual.getPassword() + "|"
                            + usuarioActual.getXP() + "\n";
                } else {
                    contenido += linea + "\n";
                }
            }

            br.close();

            FileWriter fw = new FileWriter("usuarios.txt");
            fw.write(contenido);
            fw.close();

        } catch (Exception e) {
            System.out.println("No se pudo guardar XP del usuario.");
        }
    }
    
    public static void sumarXPPorNombre(String nombre, int xpExtra) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String contenido = "";
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length >= 4 && partes[0].equalsIgnoreCase(nombre)) {
                    int xpActual = Integer.parseInt(partes[3]);
                    xpActual += xpExtra;

                    contenido += partes[0] + "|" + partes[1] + "|" + partes[2] + "|" + xpActual + "\n";
                } else {
                    contenido += linea + "\n";
                }
            }

            br.close();

            FileWriter fw = new FileWriter("usuarios.txt");
            fw.write(contenido);
            fw.close();

        } catch (Exception e) {
            System.out.println("No se pudo sumar XP por nombre");
        }
    }
    
}
