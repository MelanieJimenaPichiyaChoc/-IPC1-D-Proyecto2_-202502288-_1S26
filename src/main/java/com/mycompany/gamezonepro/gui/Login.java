/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gamezonepro.gui;
import com.mycompany.gamezonepro.gui.MenuPrincipal;
import com.mycompany.gamezonepro.logica.Sesion;
import com.mycompany.gamezonepro.modelo.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 * @author Jimena
 */
public class Login extends javax.swing.JFrame {

    
    public Login() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblJugador2 = new javax.swing.JLabel();
        lblJugador3 = new javax.swing.JLabel();
        txtCarne = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        lblJugador4 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        btnRegistrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(184, 214, 244));
        jPanel1.setForeground(new java.awt.Color(184, 214, 244));

        lblJugador2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblJugador2.setForeground(new java.awt.Color(0, 0, 0));
        lblJugador2.setText("Nombre");
        lblJugador2.setToolTipText("");

        lblJugador3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblJugador3.setForeground(new java.awt.Color(0, 0, 0));
        lblJugador3.setText("Carné");
        lblJugador3.setToolTipText("");

        txtCarne.setBackground(new java.awt.Color(255, 255, 255));
        txtCarne.setForeground(new java.awt.Color(51, 51, 51));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setForeground(new java.awt.Color(51, 51, 51));

        lblJugador4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblJugador4.setForeground(new java.awt.Color(0, 0, 0));
        lblJugador4.setText("Contraseña");
        lblJugador4.setToolTipText("");

        btnIniciar.setBackground(new java.awt.Color(204, 102, 0));
        btnIniciar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciar.setText("INICIAR");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnRegistrar1.setBackground(new java.awt.Color(204, 102, 0));
        btnRegistrar1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRegistrar1.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar1.setText("REGISTRAR");
        btnRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistrar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(136, 136, 136)
                                    .addComponent(lblJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(120, 120, 120)
                                    .addComponent(lblJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(txtCarne, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(lblJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(lblJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCarne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        String carne = txtCarne.getText().trim();
        String password = txtPassword.getText().trim();

        if (carne.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa carné y contraseña");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length == 4) {
                    String nombreGuardado = partes[0];
                    String carneGuardado = partes[1];
                    String passGuardado = partes[2];
                    int xpGuardado = Integer.parseInt(partes[3]);

                    if (carneGuardado.equals(carne) && passGuardado.equals(password)) {
                        Usuario usuario = new Usuario(nombreGuardado, carneGuardado, passGuardado);
                        usuario.setXP(xpGuardado);
                        usuario.otorgarXP(10);
                        Sesion.usuarioActual = usuario;
                        com.mycompany.gamezonepro.logica.GestorUsuarios.guardarXPUsuario(usuario);

                        br.close();

                        JOptionPane.showMessageDialog(this, "Bienvenida, " + usuario.getNombre());

                        new MenuPrincipal().setVisible(true);
                        this.dispose();
                        return;
                    }
                }
            }

            br.close();
            JOptionPane.showMessageDialog(this, "Carné o contraseña incorrectos");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No hay usuarios registrados todavía");
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar1ActionPerformed
        String nombre = txtNombre.getText().trim();
        String carne = txtCarne.getText().trim();
        String password = txtPassword.getText().trim();

        if (nombre.isEmpty() || carne.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Llena todos los campos");
            return;
        }

        if (existeUsuario(carne)) {
            JOptionPane.showMessageDialog(this, "Ese carné ya está registrado");
            return;
        }

        try {
            FileWriter fw = new FileWriter("usuarios.txt", true);
            fw.write(nombre + "|" + carne + "|" + password + "|0\n");
            fw.close();

            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");

            txtNombre.setText("");
            txtCarne.setText("");
            txtPassword.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario");
        }
    }//GEN-LAST:event_btnRegistrar1ActionPerformed

    private boolean existeUsuario(String carne) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length >= 2 && partes[1].equals(carne)) {
                    br.close();
                    return true;
                }
            }

            br.close();

        } catch (Exception e) {
            return false;
        }

        return false;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnRegistrar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblJugador3;
    private javax.swing.JLabel lblJugador4;
    private javax.swing.JTextField txtCarne;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
