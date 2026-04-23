/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gamezonepro.gui;

import com.mycompany.gamezonepro.estructuras.MallaOrtogonal;
import com.mycompany.gamezonepro.estructuras.NodoMatriz;
import com.mycompany.gamezonepro.modelo.Carta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * @author Jimenaaa
 */
public class AlbumDeCartas extends javax.swing.JFrame {
    
    private MallaOrtogonal album;
    private Carta cartaSeleccionada;
    private int filaSeleccionada = -1;
    private int columnaSeleccionada = -1;
    
    public AlbumDeCartas() {
        initComponents();

        panelVistaCarta.setBackground(java.awt.Color.LIGHT_GRAY);
        panelVistaCarta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 2));

        lblVistaNombre.setText("Sin selección");
        lblVistaRareza.setText("");

        panelCartas.setPreferredSize(new java.awt.Dimension(720, 480));
        panelDetalleCarta.setPreferredSize(new java.awt.Dimension(260, 400));

        pgbAtaque.setMinimum(0);
        pgbAtaque.setMaximum(100);

        pgbDefensa.setMinimum(0);
        pgbDefensa.setMaximum(100);

        pgbPs.setMinimum(0);
        pgbPs.setMaximum(120);

        pgbAtaque.setForeground(new java.awt.Color(230, 102, 0));
        pgbDefensa.setForeground(new java.awt.Color(51, 102, 255));
        pgbPs.setForeground(new java.awt.Color(51, 153, 51));
        pgbFila.setForeground(new java.awt.Color(153, 51, 255));

        cartaSeleccionada = null;

        cmbTipo.removeAllItems();
        cmbTipo.addItem("Todos");
        cmbTipo.addItem("Fuego");
        cmbTipo.addItem("Agua");
        cmbTipo.addItem("Planta");
        cmbTipo.addItem("Eléctrico");
        cmbTipo.addItem("Psíquico");
        cmbTipo.addItem("Animal");
        cmbTipo.addItem("Normal");
        cmbTipo.addItem("Oscuro");
        cmbTipo.addItem("Acero");

        cmbRareza.removeAllItems();
        cmbRareza.addItem("Todas");
        cmbRareza.addItem("Común");
        cmbRareza.addItem("Poco Común");
        cmbRareza.addItem("Rara");
        cmbRareza.addItem("Ultra Rara");
        cmbRareza.addItem("Legendaria");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarAlbum();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                guardarAlbum();
            }
        });

        cmbTipo.addActionListener(e -> filtrarAlbum());
        cmbRareza.addActionListener(e -> filtrarAlbum());

        cargarAlbum();
        mostrarAlbum();
    }
    
    private void guardarAlbum() {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("album.txt");

            NodoMatriz[] nodos = album.recorrer();

            for (int i = 0; i < nodos.length; i++) {
                NodoMatriz nodo = nodos[i];

                if (nodo.dato != null) {
                    Carta c = nodo.dato;

                    String linea = c.getCodigo() + "|"
                            + c.getNombre() + "|"
                            + c.getTipo() + "|"
                            + c.getRareza() + "|"
                            + c.getAtaque() + "|"
                            + c.getDefensa() + "|"
                            + c.getPs() + "|"
                            + c.getImagen();

                    fw.write(linea + "\n");
                }
            }

            fw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar álbum: " + e.getMessage());
        }
    }
    
    private void cargarAlbum() {
        album = new MallaOrtogonal();
        album.construir(4, 6);

        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("album.txt"));
            String linea;
            boolean hayCartas = false;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linea.split("\\|");

                if (partes.length >= 7) {
                    String imagen = "";

                    if (partes.length >= 8) {
                        imagen = partes[7];
                    }

                    Carta carta = new Carta(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            Integer.parseInt(partes[4]),
                            Integer.parseInt(partes[5]),
                            Integer.parseInt(partes[6]),
                            imagen
                    );

                    album.insertar(carta);
                    hayCartas = true;
                }
            }

            br.close();

            if (!hayCartas) {
                cargarCartasIniciales();
            }

        } catch (Exception e) {
            System.out.println("No existe album.txt o hubo error al cargar. Se cargarán cartas iniciales.");
            cargarCartasIniciales();
        }
    }
    
    
    private void actualizarVistaCarta(Carta c) {
        java.awt.Color colorFondo;

        switch (c.getTipo().toLowerCase()) {
            case "fuego":
                colorFondo = new java.awt.Color(230, 102, 0);
                break;
            case "agua":
                colorFondo = new java.awt.Color(30, 90, 200);
                break;
            case "planta":
                colorFondo = new java.awt.Color(40, 140, 60);
                break;
            case "eléctrico":
            case "electrico":
                colorFondo = new java.awt.Color(255, 170, 0);
                break;
            case "psíquico":
            case "psiquico":
                colorFondo = new java.awt.Color(120, 50, 180);
                break;
            case "animal":
            case "normal":
                colorFondo = new java.awt.Color(90, 120, 130);
                break;
            case "oscuro":
                colorFondo = new java.awt.Color(60, 60, 60);
                break;
            case "acero":
                colorFondo = new java.awt.Color(120, 120, 140);
                break;
            default:
                colorFondo = java.awt.Color.GRAY;
                break;
        }

        panelVistaCarta.setBackground(colorFondo);

        if (c.getRareza().equalsIgnoreCase("legendaria")) {
            panelVistaCarta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 215, 0), 4));
        } else if (c.getRareza().equalsIgnoreCase("ultra rara")) {
            panelVistaCarta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 120, 0), 3));
        } else if (c.getRareza().equalsIgnoreCase("rara")) {
            panelVistaCarta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 60, 200), 3));
        } else {
            panelVistaCarta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180), 2));
        }

        lblVistaNombre.setText(c.getNombre());
        lblVistaRareza.setText(c.getRareza());

        if (c.getTipo().equalsIgnoreCase("fuego")) {
            lblVistaIcono.setText("🔥");
        } else if (c.getTipo().equalsIgnoreCase("agua")) {
            lblVistaIcono.setText("💧");
        } else if (c.getTipo().equalsIgnoreCase("planta")) {
            lblVistaIcono.setText("🌿");
        } else if (c.getTipo().equalsIgnoreCase("eléctrico") || c.getTipo().equalsIgnoreCase("electrico")) {
            lblVistaIcono.setText("⚡");
        } else if (c.getTipo().equalsIgnoreCase("psíquico") || c.getTipo().equalsIgnoreCase("psiquico")) {
            lblVistaIcono.setText("🌀");
        } else {
            lblVistaIcono.setText("✦");
        }
    }
    
    private void filtrarAlbum() {
        String texto = txtBuscar.getText().trim().toLowerCase();

        if (texto.equals("buscar carta por nombre o tipo...")) {
            texto = "";
        }

        String tipo = cmbTipo.getSelectedItem().toString();
        String rareza = cmbRareza.getSelectedItem().toString();

        panelCartas.removeAll();
        panelCartas.setLayout(new GridLayout(4, 6, 8, 8));

        NodoMatriz[] nodos = album.recorrer();

        for (int i = 0; i < nodos.length; i++) {
            NodoMatriz nodo = nodos[i];
            int fila = i / 6;
            int col = i % 6;

            boolean resaltar = false;

            if (nodo.dato != null) {
                Carta c = nodo.dato;

                boolean coincideTexto = texto.isEmpty()
                        || c.getNombre().toLowerCase().contains(texto)
                        || c.getTipo().toLowerCase().contains(texto)
                        || c.getRareza().toLowerCase().contains(texto)
                        || c.getCodigo().toLowerCase().contains(texto);

                boolean coincideTipo = tipo.equals("Todos")
                        || c.getTipo().equalsIgnoreCase(tipo);

                boolean coincideRareza = rareza.equals("Todas")
                        || c.getRareza().equalsIgnoreCase(rareza);

                resaltar = coincideTexto && coincideTipo && coincideRareza;
            }

            panelCartas.add(crearPanelCarta(nodo, fila, col, resaltar));
        }

        limpiarDetalleSiNoCoincide(texto, tipo, rareza);

        panelCartas.revalidate();
        panelCartas.repaint();
    }
    
    private void limpiarDetalleSiNoCoincide(String texto, String tipo, String rareza) {
        if (cartaSeleccionada == null) {
            return;
        }

        boolean coincideTexto = texto.isEmpty()
                || cartaSeleccionada.getNombre().toLowerCase().contains(texto)
                || cartaSeleccionada.getTipo().toLowerCase().contains(texto)
                || cartaSeleccionada.getRareza().toLowerCase().contains(texto)
                || cartaSeleccionada.getCodigo().toLowerCase().contains(texto);

        boolean coincideTipo = tipo.equals("Todos")
                || cartaSeleccionada.getTipo().equalsIgnoreCase(tipo);

        boolean coincideRareza = rareza.equals("Todas")
                || cartaSeleccionada.getRareza().equalsIgnoreCase(rareza);

        if (!(coincideTexto && coincideTipo && coincideRareza)) {
            cartaSeleccionada = null;

            lblNombreCarta.setText("Nombre:");
            lblCodigoCarta.setText("Código:");
            lblTipoCarta.setText("Tipo:");
            lblRarezaCarta.setText("Rareza:");
            lblPosicion.setText("Posición:");

            lblValorAtaque.setText("0");
            lblValorDefensa.setText("0");
            lblValorPs.setText("0");

            pgbAtaque.setValue(0);
            pgbDefensa.setValue(0);
            pgbPs.setValue(0);
            pgbFila.setValue(0);

            lblFilaInfo.setText("Fila sin seleccionar");

            panelVistaCarta.setBackground(java.awt.Color.LIGHT_GRAY);
            lblVistaNombre.setText("Sin selección");
            lblVistaRareza.setText("");
            lblVistaIcono.setText("");
        }
    }
    
    private void cargarCartasIniciales() {
        album.insertar(new Carta("C01", "Dracora", "Fuego", "Rara", 85, 60, 90));
        album.insertar(new Carta("C02", "Aquator", "Agua", "Común", 40, 55, 70));
        album.insertar(new Carta("C03", "Voltora", "Eléctrico", "Ultra Rara", 90, 65, 100));
        album.insertar(new Carta("C04", "Bulbaza", "Planta", "Común", 35, 50, 75));
        album.insertar(new Carta("C05", "Magmora", "Fuego", "Rara", 80, 40, 85));
        album.insertar(new Carta("C06", "Tibers", "Agua", "Común", 45, 70, 95));
        album.insertar(new Carta("C07", "Forestin", "Planta", "Común", 30, 60, 80));
        album.insertar(new Carta("C08", "Zarpite", "Eléctrico", "Común", 70, 45, 65));
        album.insertar(new Carta("C09", "Vortex", "Psíquico", "Rara", 88, 50, 72));
        album.insertar(new Carta("C10", "Fawrian", "Animal", "Común", 55, 55, 55));
        album.insertar(new Carta("C11", "Dralux", "Fuego", "Legendaria", 100, 95, 120));
        album.insertar(new Carta("C12", "Mariplex", "Psíquico", "Rara", 60, 48, 78));
    }
    
    private void mostrarAlbum() {
        panelCartas.removeAll();
        panelCartas.setLayout(new GridLayout(4, 6, 8, 8));

        NodoMatriz[] nodos = album.recorrer();

        for (int i = 0; i < nodos.length; i++) {
            NodoMatriz nodo = nodos[i];
            JPanel tarjeta = crearPanelCarta(nodo, i / 6, i % 6);
            panelCartas.add(tarjeta);
        }

        panelCartas.revalidate();
        panelCartas.repaint();
    }
    
    private JPanel crearPanelCarta(NodoMatriz nodo, int fila, int col) {
        return crearPanelCarta(nodo, fila, col, false);
    }

    private JPanel crearPanelCarta(NodoMatriz nodo, int fila, int col, boolean resaltar) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        if (nodo.dato == null) {
            panel.setBackground(new Color(240, 240, 240));
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            panel.add(new JLabel("Vacía"));
            panel.add(new JLabel(""));
            panel.add(new JLabel(""));
            panel.add(new JLabel("Fila: " + fila));
            panel.add(new JLabel("Col: " + col));
        } else {
            Carta c = nodo.dato;

            if (c.esLegendaria()) {
                panel.setBackground(new Color(255, 204, 102));
            } else {
                panel.setBackground(Color.WHITE);
            }

            if (resaltar) {
                panel.setBorder(BorderFactory.createLineBorder(new Color(180, 0, 220), 4));
            } else {
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            }

            panel.add(new JLabel(c.getNombre()));
            panel.add(new JLabel(c.getTipo()));
            panel.add(new JLabel(c.getRareza()));
            panel.add(new JLabel("ATQ: " + c.getAtaque()));
            panel.add(new JLabel("PS: " + c.getPs()));

            panel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    cartaSeleccionada = c;
                    filaSeleccionada = fila;
                    columnaSeleccionada = col;
                    mostrarDetalleCarta(c, fila, col);
                }
            });
        }

        return panel;
    }
    
    private void mostrarDetalleCarta(Carta c, int fila, int col) {
        lblNombreCarta.setText("Nombre: " + c.getNombre());
        lblCodigoCarta.setText("Código: " + c.getCodigo());
        lblTipoCarta.setText("Tipo: " + c.getTipo());
        lblRarezaCarta.setText("Rareza: " + c.getRareza());

        pgbAtaque.setValue(c.getAtaque());
        pgbDefensa.setValue(c.getDefensa());
        pgbPs.setValue(c.getPs());

        lblValorAtaque.setText(String.valueOf(c.getAtaque()));
        lblValorDefensa.setText(String.valueOf(c.getDefensa()));
        lblValorPs.setText(String.valueOf(c.getPs()));

        lblFilaInfo.setText("Posición: " + fila + ", " + col);

        actualizarProgresoFila(fila);
        actualizarVistaCarta(c);
    }
    
    private void actualizarProgresoFila(int fila) {
        NodoMatriz[] nodos = album.recorrer();

        int inicio = fila * 6;
        int fin = inicio + 6;
        int ocupadas = 0;

        for (int i = inicio; i < fin && i < nodos.length; i++) {
            if (nodos[i].dato != null) {
                ocupadas++;
            }
        }

        int porcentaje = (ocupadas * 100) / 6;

        pgbFila.setMinimum(0);
        pgbFila.setMaximum(100);
        pgbFila.setValue(porcentaje);

        lblFilaInfo.setText("Fila " + fila + " completada al " + porcentaje + "%");
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelCartas = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        cmbRareza = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnIntercambiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        panelDetalleCarta = new javax.swing.JPanel();
        lblNombreCarta = new javax.swing.JLabel();
        lblCodigoCarta = new javax.swing.JLabel();
        lblTipoCarta = new javax.swing.JLabel();
        lblRarezaCarta = new javax.swing.JLabel();
        lblFilaInfo = new javax.swing.JLabel();
        pgbAtaque = new javax.swing.JProgressBar();
        lblValorAtaque = new javax.swing.JLabel();
        lblFilaInfo2 = new javax.swing.JLabel();
        lblFilaInfo3 = new javax.swing.JLabel();
        pgbDefensa = new javax.swing.JProgressBar();
        lblValorDefensa = new javax.swing.JLabel();
        lblFilaInfo5 = new javax.swing.JLabel();
        pgbPs = new javax.swing.JProgressBar();
        lblValorPs = new javax.swing.JLabel();
        lblFilaInfo7 = new javax.swing.JLabel();
        pgbFila = new javax.swing.JProgressBar();
        lblFilaInfo8 = new javax.swing.JLabel();
        panelVistaCarta = new javax.swing.JPanel();
        lblVistaNombre = new javax.swing.JLabel();
        lblVistaRareza = new javax.swing.JLabel();
        lblVistaIcono = new javax.swing.JLabel();
        lblPosicion = new javax.swing.JLabel();
        btnQuitar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(209, 225, 244));

        panelCartas.setBackground(new java.awt.Color(237, 237, 237));

        javax.swing.GroupLayout panelCartasLayout = new javax.swing.GroupLayout(panelCartas);
        panelCartas.setLayout(panelCartasLayout);
        panelCartasLayout.setHorizontalGroup(
            panelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        panelCartasLayout.setVerticalGroup(
            panelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );

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

        jPanel4.setBackground(new java.awt.Color(175, 208, 240));
        jPanel4.setForeground(new java.awt.Color(183, 227, 249));

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));
        txtBuscar.setText("Buscar carta por nombre o tipo...");

        cmbTipo.setBackground(new java.awt.Color(255, 255, 255));
        cmbTipo.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        cmbTipo.setForeground(new java.awt.Color(102, 102, 102));
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Acción", "RPG", "Estrategia", "Deportes", "Terror", "Aventura" }));

        cmbRareza.setBackground(new java.awt.Color(255, 255, 255));
        cmbRareza.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        cmbRareza.setForeground(new java.awt.Color(102, 102, 102));
        cmbRareza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "PC", "PlayStation", "Xbox", "Nintendo Switch", " " }));

        btnAgregar.setBackground(new java.awt.Color(51, 153, 255));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnIntercambiar.setBackground(new java.awt.Color(51, 153, 255));
        btnIntercambiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIntercambiar.setForeground(new java.awt.Color(255, 255, 255));
        btnIntercambiar.setText("Intercambiar");
        btnIntercambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIntercambiarActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbRareza, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIntercambiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbRareza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(btnIntercambiar)
                    .addComponent(btnBuscar))
                .addContainerGap(9, Short.MAX_VALUE))
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
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(btnRegresar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelDetalleCarta.setBackground(new java.awt.Color(255, 255, 255));

        lblNombreCarta.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblNombreCarta.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreCarta.setText(".");

        lblCodigoCarta.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblCodigoCarta.setForeground(new java.awt.Color(0, 0, 0));
        lblCodigoCarta.setText(".");

        lblTipoCarta.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblTipoCarta.setForeground(new java.awt.Color(0, 0, 0));
        lblTipoCarta.setText(".");

        lblRarezaCarta.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblRarezaCarta.setForeground(new java.awt.Color(0, 0, 0));
        lblRarezaCarta.setText(".");

        lblFilaInfo.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblFilaInfo.setForeground(new java.awt.Color(0, 0, 0));
        lblFilaInfo.setText(".");

        lblValorAtaque.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblValorAtaque.setForeground(new java.awt.Color(0, 0, 0));
        lblValorAtaque.setText("100");

        lblFilaInfo2.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblFilaInfo2.setForeground(new java.awt.Color(0, 0, 0));
        lblFilaInfo2.setText("ATK");

        lblFilaInfo3.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblFilaInfo3.setForeground(new java.awt.Color(0, 0, 0));
        lblFilaInfo3.setText("DEF");

        lblValorDefensa.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblValorDefensa.setForeground(new java.awt.Color(0, 0, 0));
        lblValorDefensa.setText("100");

        lblFilaInfo5.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblFilaInfo5.setForeground(new java.awt.Color(0, 0, 0));
        lblFilaInfo5.setText("PS");

        lblValorPs.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblValorPs.setForeground(new java.awt.Color(0, 0, 0));
        lblValorPs.setText("100");

        lblFilaInfo7.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblFilaInfo7.setForeground(new java.awt.Color(0, 0, 0));
        lblFilaInfo7.setText("ATK");

        lblFilaInfo8.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblFilaInfo8.setForeground(new java.awt.Color(0, 0, 0));
        lblFilaInfo8.setText("100");

        lblVistaNombre.setText(".");

        lblVistaRareza.setText(".");

        lblVistaIcono.setText(".");

        javax.swing.GroupLayout panelVistaCartaLayout = new javax.swing.GroupLayout(panelVistaCarta);
        panelVistaCarta.setLayout(panelVistaCartaLayout);
        panelVistaCartaLayout.setHorizontalGroup(
            panelVistaCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVistaCartaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVistaCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVistaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVistaRareza, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelVistaCartaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblVistaIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        panelVistaCartaLayout.setVerticalGroup(
            panelVistaCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVistaCartaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVistaNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVistaRareza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVistaIcono, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblPosicion.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lblPosicion.setForeground(new java.awt.Color(0, 0, 0));
        lblPosicion.setText(".");

        btnQuitar.setBackground(new java.awt.Color(51, 153, 255));
        btnQuitar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuitar.setForeground(new java.awt.Color(255, 255, 255));
        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDetalleCartaLayout = new javax.swing.GroupLayout(panelDetalleCarta);
        panelDetalleCarta.setLayout(panelDetalleCartaLayout);
        panelDetalleCartaLayout.setHorizontalGroup(
            panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetalleCartaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetalleCartaLayout.createSequentialGroup()
                        .addComponent(lblFilaInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pgbAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDetalleCartaLayout.createSequentialGroup()
                        .addComponent(lblFilaInfo3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(pgbDefensa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorDefensa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDetalleCartaLayout.createSequentialGroup()
                        .addComponent(lblFilaInfo5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(pgbPs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorPs, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDetalleCartaLayout.createSequentialGroup()
                        .addGroup(panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFilaInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRarezaCarta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTipoCarta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCodigoCarta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNombreCarta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelVistaCarta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDetalleCartaLayout.createSequentialGroup()
                        .addComponent(lblFilaInfo7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pgbFila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFilaInfo8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalleCartaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblPosicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        panelDetalleCartaLayout.setVerticalGroup(
            panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDetalleCartaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelVistaCarta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombreCarta)
                .addGap(18, 18, 18)
                .addComponent(lblCodigoCarta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTipoCarta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRarezaCarta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFilaInfo)
                .addGap(28, 28, 28)
                .addGroup(panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorAtaque)
                    .addComponent(pgbAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFilaInfo2))
                .addGap(28, 28, 28)
                .addGroup(panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorDefensa)
                    .addComponent(pgbDefensa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFilaInfo3))
                .addGap(28, 28, 28)
                .addGroup(panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorPs)
                    .addComponent(pgbPs, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFilaInfo5))
                .addGap(28, 28, 28)
                .addGroup(panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFilaInfo8)
                    .addComponent(pgbFila, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFilaInfo7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDetalleCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPosicion)
                    .addComponent(btnQuitar))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(panelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDetalleCarta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDetalleCarta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        guardarAlbum();
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String texto = txtBuscar.getText().trim();

        if (texto.isEmpty()) {
            mostrarAlbum();
            return;
        }

        NodoMatriz[] encontrados = album.buscar(texto);

        panelCartas.removeAll();
        panelCartas.setLayout(new GridLayout(4, 6, 8, 8));

        for (NodoMatriz nodo : encontrados) {
            JPanel panel = crearPanelCarta(nodo, 0, 0);
            panelCartas.add(panel);
        }

        panelCartas.revalidate();
        panelCartas.repaint();
        filtrarAlbum(); 
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnIntercambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIntercambiarActionPerformed
        if (filaSeleccionada == -1 || columnaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Primero selecciona una carta");
            return;
        }

        try {
            int fila2 = Integer.parseInt(JOptionPane.showInputDialog(this, "Fila destino:"));
            int col2 = Integer.parseInt(JOptionPane.showInputDialog(this, "Columna destino:"));

            if (fila2 < 0 || fila2 >= 4 || col2 < 0 || col2 >= 6) {
                JOptionPane.showMessageDialog(this, "Posición inválida");
                return;
            }

            album.intercambiar(filaSeleccionada, columnaSeleccionada, fila2, col2);
            mostrarAlbum();
            guardarAlbum();

            JOptionPane.showMessageDialog(this, "Cartas intercambiadas");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }//GEN-LAST:event_btnIntercambiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String codigo = JOptionPane.showInputDialog(this, "Código:");
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String tipo = JOptionPane.showInputDialog(this, "Tipo:");
        String rareza = JOptionPane.showInputDialog(this, "Rareza:");
        int ataque = Integer.parseInt(JOptionPane.showInputDialog(this, "Ataque:"));
        int defensa = Integer.parseInt(JOptionPane.showInputDialog(this, "Defensa:"));
        int ps = Integer.parseInt(JOptionPane.showInputDialog(this, "PS:"));

        Carta nueva = new Carta(codigo, nombre, tipo, rareza, ataque, defensa, ps);

        boolean insertada = album.insertar(nueva);

        if (insertada) {
            JOptionPane.showMessageDialog(this, "Carta agregada");
            mostrarAlbum();
        } else {
            JOptionPane.showMessageDialog(this, "No hay espacios vacíos");
        }
        
        if (insertada) {
            JOptionPane.showMessageDialog(this, "Carta agregada");
            mostrarAlbum();
            guardarAlbum();
        } else {
            JOptionPane.showMessageDialog(this, "No hay espacios vacíos");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        if (filaSeleccionada == -1 || columnaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Primero selecciona una carta");
            return;
        }

        album.quitar(filaSeleccionada, columnaSeleccionada);
        cartaSeleccionada = null;

        mostrarAlbum();
        guardarAlbum();

        lblNombreCarta.setText("Nombre:");
        lblCodigoCarta.setText("Código:");
        lblTipoCarta.setText("Tipo:");
        lblRarezaCarta.setText("Rareza:");
        lblPosicion.setText("Posición:");

        lblValorAtaque.setText("0");
        lblValorDefensa.setText("0");
        lblValorPs.setText("0");

        pgbAtaque.setValue(0);
        pgbDefensa.setValue(0);
        pgbPs.setValue(0);
        pgbFila.setValue(0);

        lblFilaInfo.setText("Fila sin seleccionar");
        lblVistaNombre.setText("Sin selección");
        lblVistaRareza.setText("");
        lblVistaIcono.setText("");
        panelVistaCarta.setBackground(java.awt.Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnQuitarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnIntercambiar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbRareza;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCodigoCarta;
    private javax.swing.JLabel lblFilaInfo;
    private javax.swing.JLabel lblFilaInfo2;
    private javax.swing.JLabel lblFilaInfo3;
    private javax.swing.JLabel lblFilaInfo5;
    private javax.swing.JLabel lblFilaInfo7;
    private javax.swing.JLabel lblFilaInfo8;
    private javax.swing.JLabel lblNombreCarta;
    private javax.swing.JLabel lblPosicion;
    private javax.swing.JLabel lblRarezaCarta;
    private javax.swing.JLabel lblTipoCarta;
    private javax.swing.JLabel lblValorAtaque;
    private javax.swing.JLabel lblValorDefensa;
    private javax.swing.JLabel lblValorPs;
    private javax.swing.JLabel lblVistaIcono;
    private javax.swing.JLabel lblVistaNombre;
    private javax.swing.JLabel lblVistaRareza;
    private javax.swing.JPanel panelCartas;
    private javax.swing.JPanel panelDetalleCarta;
    private javax.swing.JPanel panelVistaCarta;
    private javax.swing.JProgressBar pgbAtaque;
    private javax.swing.JProgressBar pgbDefensa;
    private javax.swing.JProgressBar pgbFila;
    private javax.swing.JProgressBar pgbPs;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
