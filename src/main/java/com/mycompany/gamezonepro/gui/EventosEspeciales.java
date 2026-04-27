/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gamezonepro.gui;

import com.mycompany.gamezonepro.estructuras.Cola;
import com.mycompany.gamezonepro.estructuras.ListaSimple;
import com.mycompany.gamezonepro.estructuras.NodoCola;
import com.mycompany.gamezonepro.estructuras.NodoSimple;
import com.mycompany.gamezonepro.hilos.Taquilla;
import com.mycompany.gamezonepro.logica.Sesion;
import com.mycompany.gamezonepro.modelo.Participante;
import com.mycompany.gamezonepro.modelo.Torneo;
import com.mycompany.gamezonepro.modelo.Usuario;
import com.mycompany.gamezonepro.logica.Sesion;
import com.mycompany.gamezonepro.logica.GestorUsuarios;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Jimena
 */
public class EventosEspeciales extends javax.swing.JFrame {
    
    private ListaSimple listaTorneos = new ListaSimple();
    private Cola colaEspera = new Cola();
    private ListaSimple ticketsVendidos = new ListaSimple();

    private Torneo torneoSeleccionado;
    private Taquilla taquilla1;
    private Taquilla taquilla2;
    
    private int vendidosT1 = 0; 
    private int vendidosT2 = 0; 
    private double totalIngresos = 0; 
    
    private Usuario usuarioActual; 

   
    public EventosEspeciales() {
        initComponents();
        
        vendidosT1 = 0;
        vendidosT2 = 0;
        totalIngresos = 0;
        actualizarResumenSesion();

        panelListaTorneos.setLayout(new GridLayout(0, 1, 10, 10));

        lblTorneoSeleccionado.setText("Torneo: ninguno");
        lblEstadoTaquilla1.setText("Taquilla 1: Libre");
        lblEstadoTaquilla2.setText("Taquilla 2: Libre");
        lblResumenVenta.setText("Vendidos: 0 | En cola: 0");

        txtCola.setEditable(false);
        txtLog.setEditable(false);

        lblVendidosT1.setText("0");
        lblVendidosT2.setText("0");
        lblTotalIngresos.setText("Q 0.00");

        cargarTorneos();
        mostrarTorneos();
        usuarioActual = Sesion.usuarioActual; 
        

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                guardarTicketsVendidos();
            }
        });
    }
    
    private void actualizarResumenSesion() {
        lblVendidosT1.setText(String.valueOf(vendidosT1));
        lblVendidosT2.setText(String.valueOf(vendidosT2));
        lblTotalIngresos.setText("Q " + String.format("%.2f", totalIngresos));
    }
    
    
    private void cargarTicketsVendidos() {
        vendidosT1 = 0;
        vendidosT2 = 0;
        totalIngresos = 0;
        ticketsVendidos = new ListaSimple();

        try {
            BufferedReader br = new BufferedReader(new FileReader("tickets_vendidos.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length == 4) {
                    String nombre = partes[0];
                    String torneo = partes[1];
                    String timestamp = partes[2];
                    double precio = Double.parseDouble(partes[3]);

                    Participante p = new Participante(nombre, torneo, timestamp);
                    ticketsVendidos.insertar(p);

                    if (partes[1].equalsIgnoreCase("GT Championship 2025")) {
                        vendidosT1++;
                    } else {
                        vendidosT2++;
                    }

                    totalIngresos += precio;
                }
            }

            br.close();
            actualizarResumenSesion();

        } catch (Exception e) {
            System.out.println("No hay tickets vendidos aún.");
            actualizarResumenSesion();
        }
    }
    
    
    private void cargarTorneos() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("torneos.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length == 7) {
                    String id = partes[0];
                    String nombre = partes[1];
                    String juego = partes[2];
                    String fechaHora = partes[3] + " " + partes[4];
                    double precio = Double.parseDouble(partes[5]);
                    int tickets = Integer.parseInt(partes[6]);

                    Torneo torneo = new Torneo(id, nombre, juego, fechaHora, precio, tickets);
                    listaTorneos.insertar(torneo);
                }
            }

            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar torneos: " + e.getMessage());
        }
    }
    
    private void mostrarTorneos() {
        panelListaTorneos.removeAll();

        NodoSimple aux = listaTorneos.getFrente();

        while (aux != null) {
            Torneo torneo = (Torneo) aux.dato;
            panelListaTorneos.add(crearPanelTorneo(torneo));
            aux = aux.siguiente;
        }

        panelListaTorneos.revalidate();
        panelListaTorneos.repaint();
    }
    
    private JPanel crearPanelTorneo(Torneo torneo) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

        JLabel lblNombre = new JLabel(torneo.getNombre());
        JLabel lblJuego = new JLabel(torneo.getJuego());
        JLabel lblFecha = new JLabel(torneo.getFecha());
        JLabel lblTickets = new JLabel("Tickets: " + torneo.getTicketsDisponibles());

        JButton btnInscribirse = new JButton("Inscribirse");

        if (!torneo.hayTickets()) {
            btnInscribirse.setEnabled(false);
        }

        btnInscribirse.addActionListener(e -> {
            torneoSeleccionado = torneo;
            lblTorneoSeleccionado.setText("Torneo: " + torneo.getNombre());
        });

        panel.add(lblNombre);
        panel.add(lblJuego);
        panel.add(lblFecha);
        panel.add(lblTickets);
        panel.add(btnInscribirse);

        return panel;
    }
    
    private void actualizarColaVisual() {
        txtCola.setText("");

        NodoCola aux = colaEspera.getFrente();

        while (aux != null) {
            Participante p = (Participante) aux.dato;
            txtCola.append(p.getNombre() + " - " + p.getTorneo() + "\n");
            aux = aux.siguiente;
        }
    }
    
    private void actualizarResumen() {
        int vendidos = ticketsVendidos.getTamanio();
        int enCola = colaEspera.getTamanio();
        lblResumenVenta.setText("Vendidos: " + vendidos + " | En cola: " + enCola);
    }
    
    public void actualizarEstadoTaquilla(int numero, String estado) {
        SwingUtilities.invokeLater(() -> {
            if (numero == 1) {
                lblEstadoTaquilla1.setText("Taquilla 1: " + estado);
            } else {
                lblEstadoTaquilla2.setText("Taquilla 2: " + estado);
            }
        });
    }

    public void actualizarColaDesdeHilo() {
        SwingUtilities.invokeLater(() -> {
            actualizarColaVisual();
            actualizarResumen();
        });
    }

    public void registrarVenta(int numero, Participante participante, Torneo torneo) {
        SwingUtilities.invokeLater(() -> {
            txtLog.append("T" + numero + " vendió ticket a: "
                    + participante.getNombre()
                    + " - Q" + torneo.getPrecioTicket() + "\n");

            if (numero == 1) {
                vendidosT1++;
            } else {
                vendidosT2++;
            }

            totalIngresos += torneo.getPrecioTicket();

            ticketsVendidos.insertar(participante);
            
            GestorUsuarios.sumarXPPorNombre(participante.getNombre(), 150);

            if (Sesion.usuarioActual != null 
                    && participante.getNombre().equalsIgnoreCase(Sesion.usuarioActual.getNombre())) {
                Sesion.usuarioActual.otorgarXP(150);
                GestorUsuarios.guardarXPUsuario(Sesion.usuarioActual);
            }

            actualizarResumen();
            actualizarResumenSesion();
            mostrarTorneos();
            guardarTicketsVendidos();

            if (!torneo.hayTickets()) {
                txtLog.append("Venta cerrada: tickets agotados\n");
            }
        });
    }

    public void registrarLog(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            txtLog.append(mensaje + "\n");
        });
    }
    
    private String leerHistorialTickets() {
        String contenido = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("tickets_vendidos.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length == 3) {
                    contenido += "Nombre: " + partes[0]
                            + "\nTorneo: " + partes[1]
                            + "\nFecha: " + partes[2]
                            + "\n-------------------------\n";
                }
            }

            br.close();

        } catch (Exception e) {
            contenido = "No hay historial de tickets todavía.";
        }

        return contenido;
    }

    private void guardarTicketsVendidos() {
        try {
            FileWriter fw = new FileWriter("tickets_vendidos.txt");

            NodoSimple aux = ticketsVendidos.getFrente();

            while (aux != null) {
                Participante p = (Participante) aux.dato;

                fw.write(p.getNombre() + "|"
                        + p.getTorneo() + "|"
                        + p.getTimestamp() + "\n");

                aux = aux.siguiente;
            }

            fw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar tickets vendidos");
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        panelInscripcion = new javax.swing.JPanel();
        lblTorneoSeleccionado = new javax.swing.JLabel();
        lblTorneoSeleccionado1 = new javax.swing.JLabel();
        txtNombreParticipante = new javax.swing.JTextField();
        btnEncolar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCola = new javax.swing.JTextArea();
        lblTorneoSeleccionado2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTorneoSeleccionado3 = new javax.swing.JLabel();
        lblEstadoTaquilla1 = new javax.swing.JLabel();
        lblAtendiendo1 = new javax.swing.JLabel();
        lblTorneoSeleccionado4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblAtendiendo2 = new javax.swing.JLabel();
        lblEstadoTaquilla2 = new javax.swing.JLabel();
        lblTorneoSeleccionado5 = new javax.swing.JLabel();
        btnIniciarVenta = new javax.swing.JButton();
        btnDetener = new javax.swing.JButton();
        btnResumVenta = new javax.swing.JButton();
        lblResumenVenta = new javax.swing.JLabel();
        scrollTorneos = new javax.swing.JScrollPane();
        panelListaTorneos = new javax.swing.JPanel();
        panelLog = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        lblVendidosT1 = new javax.swing.JLabel();
        lblVendidosT2 = new javax.swing.JLabel();
        lblTotalIngresos = new javax.swing.JLabel();
        lblTotalIngresos1 = new javax.swing.JLabel();
        lblVendidosT3 = new javax.swing.JLabel();
        lblVendidosT4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(209, 225, 244));

        jPanel2.setBackground(new java.awt.Color(53, 144, 235));

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Albúm de  cartas coleccionables");

        jPanel3.setBackground(new java.awt.Color(97, 171, 246));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnRegresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInscripcion.setBackground(new java.awt.Color(255, 255, 255));

        lblTorneoSeleccionado.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTorneoSeleccionado.setForeground(new java.awt.Color(0, 0, 0));
        lblTorneoSeleccionado.setText("Inscripción");

        lblTorneoSeleccionado1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTorneoSeleccionado1.setForeground(new java.awt.Color(0, 0, 0));
        lblTorneoSeleccionado1.setText("Nombre del participante");

        txtNombreParticipante.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreParticipante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnEncolar.setBackground(new java.awt.Color(51, 153, 255));
        btnEncolar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEncolar.setForeground(new java.awt.Color(255, 255, 255));
        btnEncolar.setText("Encolar");
        btnEncolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncolarActionPerformed(evt);
            }
        });

        txtCola.setBackground(new java.awt.Color(204, 204, 204));
        txtCola.setColumns(20);
        txtCola.setRows(5);
        jScrollPane1.setViewportView(txtCola);

        lblTorneoSeleccionado2.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTorneoSeleccionado2.setForeground(new java.awt.Color(0, 0, 0));
        lblTorneoSeleccionado2.setText("Cola de espera");

        jPanel4.setBackground(new java.awt.Color(0, 153, 0));

        lblTorneoSeleccionado3.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTorneoSeleccionado3.setForeground(new java.awt.Color(255, 255, 255));
        lblTorneoSeleccionado3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTorneoSeleccionado3.setText("Taquilla 1");

        lblEstadoTaquilla1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEstadoTaquilla1.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoTaquilla1.setText("Estado: ");

        lblAtendiendo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAtendiendo1.setForeground(new java.awt.Color(255, 255, 255));
        lblAtendiendo1.setText("Atendiendo: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTorneoSeleccionado3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEstadoTaquilla1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAtendiendo1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblTorneoSeleccionado3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoTaquilla1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAtendiendo1)
                .addGap(0, 66, Short.MAX_VALUE))
        );

        lblTorneoSeleccionado4.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTorneoSeleccionado4.setForeground(new java.awt.Color(0, 0, 0));
        lblTorneoSeleccionado4.setText("Taquillas concurrentes");

        jPanel5.setBackground(new java.awt.Color(51, 102, 255));

        lblAtendiendo2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAtendiendo2.setForeground(new java.awt.Color(255, 255, 255));
        lblAtendiendo2.setText("Atendiendo: ");

        lblEstadoTaquilla2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEstadoTaquilla2.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoTaquilla2.setText("Estado: ");

        lblTorneoSeleccionado5.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTorneoSeleccionado5.setForeground(new java.awt.Color(255, 255, 255));
        lblTorneoSeleccionado5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTorneoSeleccionado5.setText("Taquilla 2");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTorneoSeleccionado5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEstadoTaquilla2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAtendiendo2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblTorneoSeleccionado5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoTaquilla2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAtendiendo2)
                .addGap(0, 66, Short.MAX_VALUE))
        );

        btnIniciarVenta.setBackground(new java.awt.Color(51, 153, 255));
        btnIniciarVenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIniciarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarVenta.setText("Iniciar venta");
        btnIniciarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarVentaActionPerformed(evt);
            }
        });

        btnDetener.setBackground(new java.awt.Color(51, 153, 255));
        btnDetener.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDetener.setForeground(new java.awt.Color(255, 255, 255));
        btnDetener.setText("Detener");
        btnDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetenerActionPerformed(evt);
            }
        });

        btnResumVenta.setBackground(new java.awt.Color(51, 153, 255));
        btnResumVenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnResumVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnResumVenta.setText("Ver historial de tickets");
        btnResumVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumVentaActionPerformed(evt);
            }
        });

        lblResumenVenta.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblResumenVenta.setForeground(new java.awt.Color(0, 0, 0));
        lblResumenVenta.setText("Resumen Venta");

        javax.swing.GroupLayout panelInscripcionLayout = new javax.swing.GroupLayout(panelInscripcion);
        panelInscripcion.setLayout(panelInscripcionLayout);
        panelInscripcionLayout.setHorizontalGroup(
            panelInscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInscripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInscripcionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelInscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelInscripcionLayout.createSequentialGroup()
                                .addComponent(txtNombreParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEncolar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTorneoSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTorneoSeleccionado1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(lblTorneoSeleccionado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelInscripcionLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelInscripcionLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelInscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblResumenVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTorneoSeleccionado4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnResumVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInscripcionLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInscripcionLayout.createSequentialGroup()
                                .addComponent(btnIniciarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDetener, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelInscripcionLayout.setVerticalGroup(
            panelInscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInscripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTorneoSeleccionado)
                .addGap(18, 18, 18)
                .addComponent(lblTorneoSeleccionado1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreParticipante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEncolar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTorneoSeleccionado2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTorneoSeleccionado4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblResumenVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciarVenta)
                    .addComponent(btnDetener))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResumVenta)
                .addGap(65, 65, 65))
        );

        panelListaTorneos.setBackground(new java.awt.Color(237, 237, 237));

        javax.swing.GroupLayout panelListaTorneosLayout = new javax.swing.GroupLayout(panelListaTorneos);
        panelListaTorneos.setLayout(panelListaTorneosLayout);
        panelListaTorneosLayout.setHorizontalGroup(
            panelListaTorneosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        panelListaTorneosLayout.setVerticalGroup(
            panelListaTorneosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );

        scrollTorneos.setViewportView(panelListaTorneos);

        panelLog.setBackground(new java.awt.Color(255, 255, 255));

        txtLog.setBackground(new java.awt.Color(204, 204, 204));
        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane2.setViewportView(txtLog);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        lblVendidosT1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblVendidosT1.setForeground(new java.awt.Color(0, 0, 0));
        lblVendidosT1.setText("Resumen Venta");

        lblVendidosT2.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblVendidosT2.setForeground(new java.awt.Color(0, 0, 0));
        lblVendidosT2.setText("Resumen Venta");

        lblTotalIngresos.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTotalIngresos.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalIngresos.setText("Total ingresos: ");

        lblTotalIngresos1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTotalIngresos1.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalIngresos1.setText("Total ingresos: ");

        lblVendidosT3.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblVendidosT3.setForeground(new java.awt.Color(0, 0, 0));
        lblVendidosT3.setText("Vendidos (T2):");

        lblVendidosT4.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblVendidosT4.setForeground(new java.awt.Color(0, 0, 0));
        lblVendidosT4.setText("Vendidos (T1):");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblVendidosT4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVendidosT1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVendidosT3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalIngresos1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalIngresos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVendidosT2))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVendidosT1)
                    .addComponent(lblVendidosT4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVendidosT2)
                    .addComponent(lblVendidosT3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalIngresos)
                    .addComponent(lblTotalIngresos1))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLogLayout = new javax.swing.GroupLayout(panelLog);
        panelLog.setLayout(panelLogLayout);
        panelLogLayout.setHorizontalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLogLayout.setVerticalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogLayout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTorneos, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollTorneos)
                    .addComponent(panelInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        guardarTicketsVendidos();
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnEncolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncolarActionPerformed
        if (torneoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un torneo primero");
            return;
        }

        String nombre = txtNombreParticipante.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el nombre del participante");
            return;
        }

        Participante participante = new Participante(
                nombre,
                torneoSeleccionado.getNombre(),
                java.time.LocalDateTime.now().toString()
        );

        colaEspera.encolar(participante);
        txtNombreParticipante.setText("");
        actualizarColaVisual();
        actualizarResumen();
    }//GEN-LAST:event_btnEncolarActionPerformed

    private void btnIniciarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarVentaActionPerformed
        if (torneoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un torneo primero");
            return;
        }

        if (taquilla1 == null || !taquilla1.isAlive()) {
            taquilla1 = new Taquilla(colaEspera, 1, torneoSeleccionado, this);
            taquilla1.start();
        }

        if (taquilla2 == null || !taquilla2.isAlive()) {
            taquilla2 = new Taquilla(colaEspera, 2, torneoSeleccionado, this);
            taquilla2.start();
        }
    }//GEN-LAST:event_btnIniciarVentaActionPerformed

    private void btnDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetenerActionPerformed
        if (taquilla1 != null) {
            taquilla1.detener();
        }

        if (taquilla2 != null) {
            taquilla2.detener();
        }

        lblEstadoTaquilla1.setText("Taquilla 1: Detenida");
        lblEstadoTaquilla2.setText("Taquilla 2: Detenida");
    }//GEN-LAST:event_btnDetenerActionPerformed

    private void btnResumVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumVentaActionPerformed
        String historial = leerHistorialTickets();

        javax.swing.JTextArea area = new javax.swing.JTextArea(15, 35);
        area.setText(historial);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(area);

        JOptionPane.showMessageDialog(this, scroll, "Historial de tickets", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_btnResumVentaActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetener;
    private javax.swing.JButton btnEncolar;
    private javax.swing.JButton btnIniciarVenta;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnResumVenta;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAtendiendo1;
    private javax.swing.JLabel lblAtendiendo2;
    private javax.swing.JLabel lblEstadoTaquilla1;
    private javax.swing.JLabel lblEstadoTaquilla2;
    private javax.swing.JLabel lblResumenVenta;
    private javax.swing.JLabel lblTorneoSeleccionado;
    private javax.swing.JLabel lblTorneoSeleccionado1;
    private javax.swing.JLabel lblTorneoSeleccionado2;
    private javax.swing.JLabel lblTorneoSeleccionado3;
    private javax.swing.JLabel lblTorneoSeleccionado4;
    private javax.swing.JLabel lblTorneoSeleccionado5;
    private javax.swing.JLabel lblTotalIngresos;
    private javax.swing.JLabel lblTotalIngresos1;
    private javax.swing.JLabel lblVendidosT1;
    private javax.swing.JLabel lblVendidosT2;
    private javax.swing.JLabel lblVendidosT3;
    private javax.swing.JLabel lblVendidosT4;
    private javax.swing.JPanel panelInscripcion;
    private javax.swing.JPanel panelListaTorneos;
    private javax.swing.JPanel panelLog;
    private javax.swing.JScrollPane scrollTorneos;
    private javax.swing.JTextArea txtCola;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtNombreParticipante;
    // End of variables declaration//GEN-END:variables
}
