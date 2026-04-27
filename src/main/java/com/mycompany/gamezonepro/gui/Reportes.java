/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gamezonepro.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;


/**
 * @author Jimena
 */
public class Reportes extends javax.swing.JFrame {

    
    public Reportes() {
        initComponents();
    }

    private String generarNombreArchivo(String tipo) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        return LocalDateTime.now().format(formato) + "_" + tipo + ".html";
    }
    
    private void abrirArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            Desktop.getDesktop().browse(archivo.toURI());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el reporte");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bntInventario = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnTorneos = new javax.swing.JButton();
        btnAlbum = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        bntInventario.setBackground(new java.awt.Color(51, 153, 255));
        bntInventario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bntInventario.setForeground(new java.awt.Color(255, 255, 255));
        bntInventario.setText("REPORTE DE INVENTARIO DE TIENDA");
        bntInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntInventarioActionPerformed(evt);
            }
        });

        btnVentas.setBackground(new java.awt.Color(51, 153, 255));
        btnVentas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas.setText("REPORTE DE VENTAS");
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnTorneos.setBackground(new java.awt.Color(51, 153, 255));
        btnTorneos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTorneos.setForeground(new java.awt.Color(255, 255, 255));
        btnTorneos.setText("REPORTE DE TORNEOS");
        btnTorneos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTorneosActionPerformed(evt);
            }
        });

        btnAlbum.setBackground(new java.awt.Color(51, 153, 255));
        btnAlbum.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAlbum.setForeground(new java.awt.Color(255, 255, 255));
        btnAlbum.setText("REPORTE DE ALBÚM");
        btnAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlbumActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(51, 153, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntInventario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTorneos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlbum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(bntInventario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btnAlbum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnTorneos)
                .addGap(37, 37, 37)
                .addComponent(btnRegresar)
                .addGap(33, 33, 33))
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

    private void bntInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntInventarioActionPerformed
        try {
            String nombreArchivo = generarNombreArchivo("Inventario");
            FileWriter fw = new FileWriter(nombreArchivo);

            fw.write("<html><head><meta charset='UTF-8'>");
            fw.write("<style>");
            fw.write("body{font-family:Arial;background:#eef6ff;padding:30px;}");
            fw.write("h1{color:#1976d2;text-align:center;}");
            fw.write("table{width:100%;border-collapse:collapse;background:white;}");
            fw.write("th{background:#1976d2;color:white;padding:10px;}");
            fw.write("td{padding:8px;border:1px solid #ccc;text-align:center;}");
            fw.write("tr:nth-child(even){background:#f2f2f2;}");
            fw.write("</style></head><body>");

            fw.write("<h1>Reporte de Inventario de Tienda</h1>");
            fw.write("<table>");
            fw.write("<tr><th>Código</th><th>Nombre</th><th>Género</th><th>Precio</th><th>Plataforma</th><th>Stock</th><th>Descripción</th></tr>");

            BufferedReader br = new BufferedReader(new FileReader("catalogo.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");

                if (p.length == 7) {
                    fw.write("<tr>");
                    fw.write("<td>" + p[0] + "</td>");
                    fw.write("<td>" + p[1] + "</td>");
                    fw.write("<td>" + p[2] + "</td>");
                    fw.write("<td>Q" + p[3] + "</td>");
                    fw.write("<td>" + p[4] + "</td>");
                    fw.write("<td>" + p[5] + "</td>");
                    fw.write("<td>" + p[6] + "</td>");
                    fw.write("</tr>");
                }
            }

            br.close();

            fw.write("</table></body></html>");
            fw.close();

            abrirArchivo(nombreArchivo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar reporte de inventario: " + e.getMessage());
        }
    }//GEN-LAST:event_bntInventarioActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        try {
            String nombreArchivo = generarNombreArchivo("Ventas");
            FileWriter fw = new FileWriter(nombreArchivo);

            fw.write("<html><head><meta charset='UTF-8'>");
            fw.write("<style>body{font-family:Arial;background:#fff7ed;padding:30px;}h1{text-align:center;color:#c2410c;}pre{background:white;border:1px solid #ddd;padding:20px;border-radius:10px;}</style>");
            fw.write("</head><body><h1>Reporte de Ventas</h1><pre>");

            BufferedReader br = new BufferedReader(new FileReader("historial.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                fw.write(linea + "\n");
            }
            br.close();

            fw.write("</pre></body></html>");
            fw.close();
            abrirArchivo(nombreArchivo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar reporte de ventas: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnTorneosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTorneosActionPerformed
        try {
            String nombreArchivo = generarNombreArchivo("Torneos");
            FileWriter fw = new FileWriter(nombreArchivo);

            fw.write("<html><head><meta charset='UTF-8'>");
            fw.write("<style>");
            fw.write("body{font-family:Arial;background:#fff1f2;padding:30px;}");
            fw.write("h1{text-align:center;color:#be123c;}");
            fw.write("h2{color:#9f1239;}");
            fw.write("table{width:100%;border-collapse:collapse;background:white;margin-bottom:25px;}");
            fw.write("th{background:#be123c;color:white;padding:10px;}");
            fw.write("td{padding:8px;border:1px solid #ccc;text-align:center;}");
            fw.write("tr:nth-child(even){background:#ffe4e6;}");
            fw.write("</style></head><body>");

            fw.write("<h1>Reporte de Torneos</h1>");
            fw.write("<h2>Torneos disponibles</h2>");
            fw.write("<table><tr><th>ID</th><th>Nombre</th><th>Juego</th><th>Fecha</th><th>Hora</th><th>Precio</th><th>Tickets</th></tr>");

            BufferedReader br = new BufferedReader(new FileReader("torneos.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                if (p.length == 7) {
                    fw.write("<tr>");
                    for (int i = 0; i < p.length; i++) {
                        fw.write("<td>" + p[i] + "</td>");
                    }
                    fw.write("</tr>");
                }
            }
            br.close();

            fw.write("</table>");

            fw.write("<h2>Tickets vendidos</h2>");
            fw.write("<table><tr><th>Nombre</th><th>Torneo</th><th>Fecha/Hora</th></tr>");

            try {
                br = new BufferedReader(new FileReader("tickets_vendidos.txt"));
                while ((linea = br.readLine()) != null) {
                    String[] p = linea.split("\\|");
                    if (p.length >= 3) {
                        fw.write("<tr><td>" + p[0] + "</td><td>" + p[1] + "</td><td>" + p[2] + "</td></tr>");
                    }
                }
                br.close();
            } catch (Exception ex) {
                fw.write("<tr><td colspan='3'>No hay tickets vendidos</td></tr>");
            }

            fw.write("</table></body></html>");
            fw.close();
            abrirArchivo(nombreArchivo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar reporte de torneos: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTorneosActionPerformed

    private void btnAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlbumActionPerformed
        try {
            String nombreArchivo = generarNombreArchivo("Album");
            FileWriter fw = new FileWriter(nombreArchivo);

            fw.write("<html><head><meta charset='UTF-8'>");
            fw.write("<style>");
            fw.write("body{font-family:Arial;background:#f5f3ff;padding:30px;}");
            fw.write("h1{text-align:center;color:#6d28d9;}");
            fw.write("table{width:100%;border-collapse:collapse;background:white;}");
            fw.write("td{width:16%;height:90px;border:2px solid #ddd;text-align:center;padding:8px;}");
            fw.write(".vacia{background:#e5e7eb;color:#777;}");
            fw.write(".legendaria{background:#fde68a;font-weight:bold;}");
            fw.write(".carta{background:#ffffff;}");
            fw.write("</style></head><body>");
            fw.write("<h1>Reporte del Álbum</h1>");
            fw.write("<table>");

            BufferedReader br = new BufferedReader(new FileReader("album.txt"));
            String[][] celdas = new String[4][6];
            String linea;
            int pos = 0;

            while ((linea = br.readLine()) != null && pos < 24) {
                String[] p = linea.split("\\|");
                if (p.length >= 7) {
                    int f = pos / 6;
                    int c = pos % 6;
                    String clase = p[3].equalsIgnoreCase("Legendaria") ? "legendaria" : "carta";
                    celdas[f][c] = "<td class='" + clase + "'><b>" + p[1] + "</b><br>"
                            + p[2] + "<br>" + p[3] + "<br>ATQ:" + p[4] + " DEF:" + p[5] + " PS:" + p[6] + "</td>";
                    pos++;
                }
            }
            br.close();

            for (int i = 0; i < 4; i++) {
                fw.write("<tr>");
                for (int j = 0; j < 6; j++) {
                    if (celdas[i][j] == null) {
                        fw.write("<td class='vacia'>Vacía</td>");
                    } else {
                        fw.write(celdas[i][j]);
                    }
                }
                fw.write("</tr>");
            }

            fw.write("</table></body></html>");
            fw.close();
            abrirArchivo(nombreArchivo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar reporte del álbum: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAlbumActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        new MenuPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntInventario;
    private javax.swing.JButton btnAlbum;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnTorneos;
    private javax.swing.JButton btnVentas;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
