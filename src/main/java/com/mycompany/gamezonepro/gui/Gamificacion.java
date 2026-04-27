/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gamezonepro.gui;
import com.mycompany.gamezonepro.logica.Sesion;
import com.mycompany.gamezonepro.modelo.Usuario;
import com.mycompany.gamezonepro.estructuras.ListaSimple;
import com.mycompany.gamezonepro.estructuras.NodoSimple;
import com.mycompany.gamezonepro.logica.GestorUsuarios;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author pichi
 */
public class Gamificacion extends javax.swing.JFrame {

    private Usuario usuarioActual; 
    private int logrosDesbloqueados; 

    public Gamificacion() {
        initComponents();

        usuarioActual = Sesion.usuarioActual;

        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, "No hay usuario activo");
            new Login().setVisible(true);
            this.dispose();
            return;
        }

        aplicarRecompensasDeLogros();
        aplicarRecompensasDeNivel();
        actualizarPantalla();
    }
    
    private void aplicarRecompensasDeNivel() {
        int xp = usuarioActual.getXP();

        if (usuarioActual.getNivel() >= 2 && xp < 1000) {
            usuarioActual.otorgarXP(500);
        }

        if (xp >= 1500 && xp < 3100) {
            usuarioActual.otorgarXP(1500);
        }

        if (usuarioActual.getNivel() >= 5 && xp < 14000) {
            usuarioActual.otorgarXP(7000);
        }

        GestorUsuarios.guardarXPUsuario(usuarioActual);
    }

    private void configurarUsuario() {
        labelJugador.setText(usuarioActual.getNombre());
        labelXP.setText(usuarioActual.getXP() + " XP");
        lblXPPB.setText(usuarioActual.getXP() + "/10000 XP");
        lblNivel.setText("Nivel " + usuarioActual.getNivel() + " - " + usuarioActual.getRango());

        progressNivel.setMinimum(0);
        progressNivel.setMaximum(10000);
        progressNivel.setValue(usuarioActual.getXP());
    }
    
    private void mostrarLogros() {
        panelLogros.removeAll();
        panelLogros.setLayout(new GridLayout(0, 1, 8, 8));

        logrosDesbloqueados = 0;
        int xp = usuarioActual.getXP();

        agregarLogro("Primer Inicio", "Iniciar sesión por primera vez", 10, xp >= 10);
        agregarLogro("Primera Compra", "Realizar una compra en la tienda", 50, xp >= 60);
        agregarLogro("Participante", "Comprar ticket para torneo", 150, xp >= 210);
        agregarLogro("Coleccionista", "Agregar cartas al álbum", 100, xp >= 310);
        agregarLogro("Carta Legendaria", "Agregar una carta legendaria", 200, xp >= 510);
        agregarLogro("Jugador Nivel 2", "Alcanzar nivel 2", 500, usuarioActual.getNivel() >= 2);
        agregarLogro("Veterano", "Alcanzar 1500 XP", 1500, xp >= 1500);
        agregarLogro("Leyenda", "Alcanzar nivel 5", 7000, usuarioActual.getNivel() >= 5);

        lblResumenLogros.setText("Logros: " + logrosDesbloqueados + "/8 desbloqueados");

        panelLogros.revalidate();
        panelLogros.repaint();
    }

    private void agregarLogro(String nombre, String descripcion, int xpPremio, boolean desbloqueado) {
        JPanel card = new JPanel(new GridLayout(2, 1));
        card.setBorder(BorderFactory.createLineBorder(new Color(180, 220, 180), 2));

        if (desbloqueado) {
            card.setBackground(new Color(235, 255, 235));
            logrosDesbloqueados++;
        } else {
            card.setBackground(new Color(235, 235, 235));
        }

        JLabel lbl1 = new JLabel((desbloqueado ? "✓ " : "🔒 ") + nombre + "   recompensa: +" + xpPremio + " XP");
        JLabel lbl2 = new JLabel(descripcion);

        card.add(lbl1);
        card.add(lbl2);

        panelLogros.add(card);
    }
    
   private void mostrarLeaderboard() {
        panelLeaderboard.removeAll();
        panelLeaderboard.setLayout(new GridLayout(0, 1, 4, 4));

        ListaSimple usuarios = cargarUsuariosLeaderboard();
        ordenarUsuariosPorXP(usuarios);

        NodoSimple aux = usuarios.getFrente();
        int posicion = 1;

        while (aux != null) {
            Usuario u = (Usuario) aux.dato;

            JLabel fila = new JLabel(posicion + ". " + u.getNombre() + " - " + u.getXP() + " XP");

            if (usuarioActual != null && u.getCarne().equals(usuarioActual.getCarne())) {
                fila.setOpaque(true);
                fila.setBackground(new Color(200, 255, 200));
            }

            panelLeaderboard.add(fila);

            aux = aux.siguiente;
            posicion++;
        }

        panelLeaderboard.revalidate();
        panelLeaderboard.repaint();
    }
   
    private ListaSimple cargarUsuariosLeaderboard() {
        ListaSimple usuarios = new ListaSimple();

        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("usuarios.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length == 4) {
                    Usuario u = new Usuario(partes[0], partes[1], partes[2]);
                    u.setXP(Integer.parseInt(partes[3]));
                    usuarios.insertar(u);
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("No se pudo cargar leaderboard.");
        }

        return usuarios;
    }
    
  
    
    private void ordenarUsuariosPorXP(ListaSimple usuarios) {
        boolean cambio = true;

        while (cambio) {
            cambio = false;
            NodoSimple actual = usuarios.getFrente();

            while (actual != null && actual.siguiente != null) {
                Usuario u1 = (Usuario) actual.dato;
                Usuario u2 = (Usuario) actual.siguiente.dato;

                if (u1.getXP() < u2.getXP()) {
                    Object temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    cambio = true;
                }

                actual = actual.siguiente;
            }
        }
    }
    
    private void ordenarPorXP(String[] nombres, int[] xp) {
        for (int i = 1; i < xp.length; i++) {
            int xpActual = xp[i];
            String nombreActual = nombres[i];
            int j = i - 1;

            while (j >= 0 && xp[j] < xpActual) {
                xp[j + 1] = xp[j];
                nombres[j + 1] = nombres[j];
                j--;
            }

            xp[j + 1] = xpActual;
            nombres[j + 1] = nombreActual;
        }
    }
    
    private void actualizarPantalla() {
        usuarioActual = Sesion.usuarioActual;

        if (usuarioActual == null) {
            return;
        }

        configurarUsuario();
        mostrarLogros();
        mostrarLeaderboard();
    }
    
    private void aplicarRecompensasDeLogros() {
    int xp = usuarioActual.getXP();

    if (usuarioActual.getNivel() >= 2 && xp < 1000) {
        usuarioActual.otorgarXP(500);
        GestorUsuarios.guardarXPUsuario(usuarioActual);
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblJugador6 = new javax.swing.JLabel();
        labelXP = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        lblXPPB = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        progressNivel = new javax.swing.JProgressBar();
        jPanel35 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        labelJugador = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        panelLogros = new javax.swing.JPanel();
        lblResumenLogros = new javax.swing.JLabel();
        panelLeaderboard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel19.setBackground(new java.awt.Color(225, 235, 255));

        jPanel20.setBackground(new java.awt.Color(53, 144, 235));

        jLabel14.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Gamificación");

        jPanel21.setBackground(new java.awt.Color(97, 171, 246));

        lblJugador6.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblJugador6.setForeground(new java.awt.Color(255, 255, 255));
        lblJugador6.setText("XP acumulado");
        lblJugador6.setToolTipText("");

        labelXP.setFont(new java.awt.Font("OCR A Extended", 1, 12)); // NOI18N
        labelXP.setForeground(new java.awt.Color(255, 255, 255));
        labelXP.setText("8420 XP");
        labelXP.setToolTipText("");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJugador6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(labelXP, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(lblJugador6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(labelXP)))
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(175, 208, 240));
        jPanel22.setForeground(new java.awt.Color(183, 227, 249));

        lblXPPB.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblXPPB.setForeground(new java.awt.Color(21, 129, 237));
        lblXPPB.setText("8420/10000 XP");
        lblXPPB.setToolTipText("");

        lblNivel.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblNivel.setForeground(new java.awt.Color(204, 102, 0));
        lblNivel.setText("Niv. 5");
        lblNivel.setToolTipText("");

        progressNivel.setBackground(new java.awt.Color(129, 188, 248));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(progressNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblXPPB, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(lblNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(progressNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblXPPB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel35.setBackground(new java.awt.Color(97, 171, 246));

        labelJugador.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        labelJugador.setForeground(new java.awt.Color(255, 255, 255));
        labelJugador.setText("Jugador");
        labelJugador.setToolTipText("");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addGap(12, 12, 12)
                .addComponent(labelJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelJugador)
                        .addContainerGap())))
        );

        btnRegresar.setBackground(new java.awt.Color(51, 153, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        panelLogros.setBackground(new java.awt.Color(255, 255, 255));
        panelLogros.setForeground(new java.awt.Color(255, 255, 255));

        lblResumenLogros.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblResumenLogros.setForeground(new java.awt.Color(0, 0, 0));
        lblResumenLogros.setText("Logros: ");
        lblResumenLogros.setToolTipText("");

        javax.swing.GroupLayout panelLogrosLayout = new javax.swing.GroupLayout(panelLogros);
        panelLogros.setLayout(panelLogrosLayout);
        panelLogrosLayout.setHorizontalGroup(
            panelLogrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResumenLogros, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(283, Short.MAX_VALUE))
        );
        panelLogrosLayout.setVerticalGroup(
            panelLogrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResumenLogros)
                .addContainerGap(454, Short.MAX_VALUE))
        );

        panelLeaderboard.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLeaderboardLayout = new javax.swing.GroupLayout(panelLeaderboard);
        panelLeaderboard.setLayout(panelLeaderboardLayout);
        panelLeaderboardLayout.setHorizontalGroup(
            panelLeaderboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLeaderboardLayout.setVerticalGroup(
            panelLeaderboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLogros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLeaderboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLogros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLeaderboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel labelJugador;
    private javax.swing.JLabel labelXP;
    private javax.swing.JLabel lblJugador;
    private javax.swing.JLabel lblJugador1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblJugador3;
    private javax.swing.JLabel lblJugador4;
    private javax.swing.JLabel lblJugador5;
    private javax.swing.JLabel lblJugador6;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblPosicion;
    private javax.swing.JLabel lblPosicion1;
    private javax.swing.JLabel lblPosicion2;
    private javax.swing.JLabel lblPosicion3;
    private javax.swing.JLabel lblPosicion4;
    private javax.swing.JLabel lblPosicion5;
    private javax.swing.JLabel lblResumenLogros;
    private javax.swing.JLabel lblXP;
    private javax.swing.JLabel lblXP1;
    private javax.swing.JLabel lblXP2;
    private javax.swing.JLabel lblXP3;
    private javax.swing.JLabel lblXP4;
    private javax.swing.JLabel lblXP5;
    private javax.swing.JLabel lblXPPB;
    private javax.swing.JPanel panelLeaderboard;
    private javax.swing.JPanel panelLogros;
    private javax.swing.JProgressBar progressNivel;
    // End of variables declaration//GEN-END:variables
}
