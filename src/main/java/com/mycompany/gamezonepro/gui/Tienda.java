/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gamezonepro.gui;

import com.mycompany.gamezonepro.modelo.Juego;
import com.mycompany.gamezonepro.estructuras.ListaSimple;
import com.mycompany.gamezonepro.modelo.Usuario;
import com.mycompany.gamezonepro.estructuras.NodoSimple;
import com.mycompany.gamezonepro.logica.Sesion;
import com.mycompany.gamezonepro.modelo.ItemCarrito;
import com.mycompany.gamezonepro.modelo.Compra;
import com.mycompany.gamezonepro.logica.GestorUsuarios;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;


/**
 * @author Jimena
 */
public class Tienda extends javax.swing.JFrame {
    
    private ListaSimple listaJuegos = new ListaSimple();
    private Usuario usuarioActual; 
    private Juego juegoSeleccionado;
    private ListaSimple carrito = new ListaSimple();
    private ListaSimple historial = new ListaSimple();
   

    public Tienda() {
        initComponents();

        usuarioActual = Sesion.usuarioActual;
        juegoSeleccionado = null;
        usuarioActual.otorgarXP(50);
        GestorUsuarios.guardarXPUsuario(usuarioActual);
        mostrarUsuario();

        cmbGenero.removeAllItems();
        cmbGenero.addItem("Todos");
        cmbGenero.addItem("Acción");
        cmbGenero.addItem("RPG");
        cmbGenero.addItem("Estrategia");
        cmbGenero.addItem("Deportes");
        cmbGenero.addItem("Terror");
        cmbGenero.addItem("Aventura");

        cmbPlataforma.removeAllItems();
        cmbPlataforma.addItem("Todas");
        cmbPlataforma.addItem("PC");
        cmbPlataforma.addItem("PlayStation");
        cmbPlataforma.addItem("Xbox");
        cmbPlataforma.addItem("Nintendo Switch");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarCatalogo();
            }
        });

        cmbGenero.addActionListener(e -> filtrarCatalogo());
        cmbPlataforma.addActionListener(e -> filtrarCatalogo());

        panelCatalogo.setLayout(new GridLayout(0, 3, 15, 15));

        cargarJuegos();
        mostrarCatalogo(listaJuegos);
        mostrarCarrito();
      
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblJugador = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        lblXP = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblJugador1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        cmbGenero = new javax.swing.JComboBox<>();
        cmbPlataforma = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        lblJugador2 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        scrollCatalogo = new javax.swing.JScrollPane();
        panelCatalogo = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        btnComprar = new javax.swing.JButton();
        btnVaciar = new javax.swing.JButton();
        scrollCarrito = new javax.swing.JScrollPane();
        txtCarrito = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(209, 225, 244));

        jPanel2.setBackground(new java.awt.Color(53, 144, 235));

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tienda de Videojuegos");

        jPanel3.setBackground(new java.awt.Color(97, 171, 246));

        lblJugador.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblJugador.setForeground(new java.awt.Color(255, 255, 255));
        lblJugador.setText("Jugado 1");
        lblJugador.setToolTipText("");

        lblNivel.setFont(new java.awt.Font("OCR A Extended", 1, 12)); // NOI18N
        lblNivel.setForeground(new java.awt.Color(255, 255, 255));
        lblNivel.setText("Leyenda,");
        lblNivel.setToolTipText("");

        lblXP.setFont(new java.awt.Font("OCR A Extended", 1, 12)); // NOI18N
        lblXP.setForeground(new java.awt.Color(255, 255, 255));
        lblXP.setText("8420 XP");
        lblXP.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblXP, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblJugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNivel)
                            .addComponent(lblXP))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(175, 208, 240));
        jPanel4.setForeground(new java.awt.Color(183, 227, 249));

        lblJugador1.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblJugador1.setForeground(new java.awt.Color(21, 129, 237));
        lblJugador1.setText("Filtros: ");
        lblJugador1.setToolTipText("");

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));

        cmbGenero.setBackground(new java.awt.Color(255, 255, 255));
        cmbGenero.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        cmbGenero.setForeground(new java.awt.Color(102, 102, 102));
        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Acción", "RPG", "Estrategia", "Deportes", "Terror", "Aventura" }));

        cmbPlataforma.setBackground(new java.awt.Color(255, 255, 255));
        cmbPlataforma.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        cmbPlataforma.setForeground(new java.awt.Color(102, 102, 102));
        cmbPlataforma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "PC", "PlayStation", "Xbox", "Nintendo Switch", " " }));

        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBuscar))
                        .addContainerGap(9, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblJugador2.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblJugador2.setForeground(new java.awt.Color(0, 0, 0));
        lblJugador2.setText("Catálogo: ");
        lblJugador2.setToolTipText("");

        btnRegresar.setBackground(new java.awt.Color(51, 153, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        scrollCatalogo.setBackground(new java.awt.Color(209, 225, 244));

        panelCatalogo.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelCatalogoLayout = new javax.swing.GroupLayout(panelCatalogo);
        panelCatalogo.setLayout(panelCatalogoLayout);
        panelCatalogoLayout.setHorizontalGroup(
            panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        panelCatalogoLayout.setVerticalGroup(
            panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        scrollCatalogo.setViewportView(panelCatalogo);

        lblTotal.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal.setText("jLabel1");

        btnComprar.setBackground(new java.awt.Color(51, 153, 255));
        btnComprar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnComprar.setForeground(new java.awt.Color(255, 255, 255));
        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        btnVaciar.setBackground(new java.awt.Color(51, 153, 255));
        btnVaciar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVaciar.setForeground(new java.awt.Color(255, 255, 255));
        btnVaciar.setText("Vaciar");
        btnVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaciarActionPerformed(evt);
            }
        });

        txtCarrito.setBackground(new java.awt.Color(255, 255, 255));
        txtCarrito.setColumns(20);
        txtCarrito.setRows(5);
        scrollCarrito.setViewportView(txtCarrito);

        txtHistorial.setBackground(new java.awt.Color(255, 255, 255));
        txtHistorial.setColumns(20);
        txtHistorial.setRows(5);
        jScrollPane1.setViewportView(txtHistorial);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollCarrito)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnVaciar, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addComponent(lblJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollCatalogo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(scrollCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVaciar)
                        .addComponent(btnComprar)))
                .addGap(9, 9, 9))
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

    
    
    private void cargarHistorial() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("historial.txt"));
            String linea;

            txtHistorial.setText("");

            while ((linea = br.readLine()) != null) {
                txtHistorial.append(linea + "\n");
            }

            br.close();

        } catch (Exception e) {
            System.out.println("No hay historial aún.");
        }
    }
    
    private void filtrarCatalogo() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        String genero = cmbGenero.getSelectedItem().toString();
        String plataforma = cmbPlataforma.getSelectedItem().toString();

        ListaSimple filtrada = new ListaSimple();
        NodoSimple aux = listaJuegos.getFrente();

        while (aux != null) {
            Juego juego = (Juego) aux.dato;

            boolean coincideTexto = juego.getNombre().toLowerCase().contains(texto)
                    || juego.getCodigo().toLowerCase().contains(texto);

            boolean coincideGenero = genero.equals("Todos")
                    || juego.getGenero().equalsIgnoreCase(genero);

            boolean coincidePlataforma = plataforma.equals("Todas")
                    || juego.getPlataforma().equalsIgnoreCase(plataforma);

            if (coincideTexto && coincideGenero && coincidePlataforma) {
                filtrada.insertar(juego);
            }

            aux = aux.siguiente;
        }

        mostrarCatalogo(filtrada);
    }
    
    private void mostrarCarrito() {
        String contenido = "";
        double total = 0;

        NodoSimple aux = carrito.getFrente();

        while (aux != null) {
            ItemCarrito item = (ItemCarrito) aux.dato;
            double subtotal = item.getSubtotal();
            total += subtotal;

            contenido += item.getJuego().getNombre()
                    + " x" + item.getCantidad()
                    + " = Q" + subtotal + "\n";

            aux = aux.siguiente;
        }

        txtCarrito.setText(contenido);
        lblTotal.setText("Total: Q" + total);
    }   
    
    private void mostrarUsuario() {
        lblJugador.setText(usuarioActual.getNombre());
        lblNivel.setText("Nivel " + usuarioActual.getNivel());
        lblXP.setText(usuarioActual.getXP() + " XP");
    }
    
    private void cargarJuegos() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("catalogo.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length == 7) {
                    String codigo = partes[0];
                    String nombre = partes[1];
                    String genero = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    String plataforma = partes[4];
                    int stock = Integer.parseInt(partes[5]);
                    String descripcion = partes[6];

                    Juego juego = new Juego(codigo, nombre, genero, plataforma, precio, stock, descripcion);
                    listaJuegos.insertar(juego);
                }
            }

            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar catálogo: " + e.getMessage());
        }
    }
    
    private void mostrarCatalogo(ListaSimple lista) {

        panelCatalogo.removeAll();

        NodoSimple aux = lista.getFrente();

        while (aux != null) {
            Juego juego = (Juego) aux.dato;

           
            panelCatalogo.add(crearTarjetaJuego(juego));

            aux = aux.siguiente;
        }

        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }
    
    private void guardarCatalogo() {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("catalogo.txt");

            NodoSimple aux = listaJuegos.getFrente();

            while (aux != null) {
                Juego j = (Juego) aux.dato;

                fw.write(j.getCodigo() + "|"
                        + j.getNombre() + "|"
                        + j.getGenero() + "|"
                        + j.getPrecio() + "|"
                        + j.getPlataforma() + "|"
                        + j.getStock() + "|"
                        + j.getDescripcion() + "\n");

                aux = aux.siguiente;
            }

            fw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar catálogo: " + e.getMessage());
        }
    }
    
    private void guardarHistorial() {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("historial.txt");

            NodoSimple aux = historial.getFrente();

            while (aux != null) {
                Compra c = (Compra) aux.dato;
                fw.write(c.getResumen() + "\n");
                aux = aux.siguiente;
            }

            fw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar historial");
        }
    }
    
    private void mostrarHistorial() {

        txtHistorial.setText("");

        NodoSimple aux = historial.getFrente();

        while (aux != null) {
            Compra c = (Compra) aux.dato;

            txtHistorial.append(c.getResumen() + "\n");

            aux = aux.siguiente;
        }
    }
    
    
    private JPanel crearTarjetaJuego(Juego juego) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setPreferredSize(new Dimension(180, 160));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));

        JLabel lblNombre = new JLabel(juego.getNombre());
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblGenero = new JLabel("Género: " + juego.getGenero());
        JLabel lblPlataforma = new JLabel("Plataforma: " + juego.getPlataforma());
        JLabel lblPrecio = new JLabel("Precio: Q" + juego.getPrecio());
        JLabel lblStock = new JLabel("Stock: " + juego.getStock());

        JButton btnAgregar = new JButton("Agregar");

        btnAgregar.addActionListener(e -> {
            NodoSimple aux = carrito.getFrente();
            boolean existe = false;

            while (aux != null) {
                ItemCarrito item = (ItemCarrito) aux.dato;

                if (item.getJuego().getCodigo().equals(juego.getCodigo())) {
                    item.setCantidad(item.getCantidad() + 1);
                    existe = true;
                    break;
                }

                aux = aux.siguiente;
            }

            if (!existe) {
                carrito.insertar(new ItemCarrito(juego, 1));
            }

            mostrarCarrito();
            JOptionPane.showMessageDialog(this, "Agregado al carrito: " + juego.getNombre());
        });

        tarjeta.add(lblNombre);
        tarjeta.add(lblGenero);
        tarjeta.add(lblPlataforma);
        tarjeta.add(lblPrecio);
        tarjeta.add(lblStock);
        tarjeta.add(btnAgregar);

        return tarjeta;
    }
    
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        guardarCatalogo();
        guardarHistorial();

        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        if (carrito.estaVacia()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío");
            return;
        }

        NodoSimple aux = carrito.getFrente();

        while (aux != null) {
            ItemCarrito item = (ItemCarrito) aux.dato;

            if (item.getJuego().getStock() < item.getCantidad()) {
                JOptionPane.showMessageDialog(this,
                        "No hay suficiente stock para: " + item.getJuego().getNombre());
                return;
            }

            aux = aux.siguiente;
        }

        Compra compra = new Compra();
        compra.setId("CMP-" + System.currentTimeMillis());
        compra.setFecha(java.time.LocalDateTime.now().toString());
        compra.setItems(carrito);

        double total = 0;
        aux = carrito.getFrente();

        while (aux != null) {
            ItemCarrito item = (ItemCarrito) aux.dato;
            item.getJuego().reducirStock(item.getCantidad());
            total += item.getSubtotal();
            aux = aux.siguiente;
        }

        compra.setTotal(total);
        historial.insertarAlInicio(compra);

        JOptionPane.showMessageDialog(this, "Compra realizada con éxito");

        carrito = new ListaSimple();
        mostrarCarrito();
        mostrarCatalogo(listaJuegos);
        mostrarHistorial();
        guardarCatalogo();
    }//GEN-LAST:event_btnComprarActionPerformed

    private void btnVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarActionPerformed
        carrito = new ListaSimple();
        mostrarCarrito();
    }//GEN-LAST:event_btnVaciarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVaciar;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JComboBox<String> cmbPlataforma;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblJugador;
    private javax.swing.JLabel lblJugador1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblXP;
    private javax.swing.JPanel panelCatalogo;
    private javax.swing.JScrollPane scrollCarrito;
    private javax.swing.JScrollPane scrollCatalogo;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtCarrito;
    private javax.swing.JTextArea txtHistorial;
    // End of variables declaration//GEN-END:variables
}
