/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gamezonepro;

import com.mycompany.gamezonepro.gui.MenuPrincipal;

/**
 *
 * @author pichi
 */
public class GameZonePro {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
}
