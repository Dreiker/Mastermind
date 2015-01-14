/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Manuel
 */
public class MastermindDificil extends javax.swing.JFrame implements Runnable {

    /**
     * Número de botones que hay en posición horizontal.
     */
    final int NUM_BOTONES_ANCHO = 4;
    /**
     * Número de botones que hay en posición vertical.
     */
    final int NUM_BOTONES_LARGO = 8;

    boolean rojoUsado = false;
    boolean verdeUsado = false;
    boolean azulUsado = false;
    boolean amarilloUsado = false;
    char combinacionSecreta[] = new char[4];
    
    boolean contar = true;
    int posicionY = 0;
    int posicionX = -1;
    int intentos = 0;
    int minutos = 0;
    int segundos = 25;

    final char COLOR_ROJO = 'R';
    final char COLOR_VERDE = 'G';
    final char COLOR_AZUL = 'B';
    final char COLOR_AMARILLO = 'Y';

    JLabel[][] botones;
    JLabel[][] pistas;

    public MastermindDificil() {
        initComponents();

        Thread thread = new Thread(this);
        thread.start();

        // Inicializar componentes de la ventana.
        JLabel[][] botones = {
            {espacio1, espacio2, espacio3, espacio4},
            {espacio5, espacio6, espacio7, espacio8},
            {espacio9, espacio10, espacio11, espacio12},
            {espacio13, espacio14, espacio15, espacio16},
            {espacio17, espacio18, espacio19, espacio20},
            {espacio21, espacio22, espacio23, espacio24},
            {espacio25, espacio26, espacio27, espacio28},
            {espacio29, espacio30, espacio31, espacio32}
        };

        JLabel[][] pistas = {
            {pista1, pista2, pista3, pista4},
            {pista5, pista6, pista7, pista8},
            {pista9, pista10, pista11, pista12},
            {pista13, pista14, pista15, pista16},
            {pista17, pista18, pista19, pista20},
            {pista21, pista22, pista23, pista24},
            {pista25, pista26, pista27, pista28},
            {pista29, pista30, pista31, pista32}
        };
        this.botones = botones;
        this.pistas = pistas;

        //Centrar ventana.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        generarClave();
    }
    //Generar combinación secreta.
    private void generarClave() {

        rojoUsado = false;
        azulUsado = false;
        verdeUsado = false;
        amarilloUsado = false;
        
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            switch (random.nextInt(4)) {
                case 0:
                    if (rojoUsado) {
                        i--;
                    } else {
                        combinacionSecreta[i] = COLOR_ROJO;
                        rojoUsado = true;
                    }
                    break;

                case 1:
                    if (verdeUsado) {
                        i--;
                    } else {
                        combinacionSecreta[i] = COLOR_VERDE;
                        verdeUsado = true;
                    }
                    break;

                case 2:
                    if (azulUsado) {
                        i--;
                    } else {
                        combinacionSecreta[i] = COLOR_AZUL;
                        azulUsado = true;
                    }
                    break;

                case 3:
                    if (amarilloUsado) {
                        i--;
                    } else {
                        combinacionSecreta[i] = COLOR_AMARILLO;
                        amarilloUsado = true;
                    }
                    break;
            }
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(combinacionSecreta[i]);
        }
    }

    private Color obtenerColor(char c) {
        if (c == 'R') {
            return Color.RED;
        } else if (c == 'G') {
            return Color.GREEN;
        } else if (c == 'B') {
            return Color.BLUE;
        } else if (c == 'Y') {
            return Color.YELLOW;
        }

        return null;
    }

    private void comprobarCasilla() {

        for (int i = 0; i < 4; i++) {
            if (botones[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + i].getBackground() == obtenerColor(combinacionSecreta[i])) {
                pistas[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + i].setBackground(Color.GREEN);
            } else {
                pistas[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + i].setBackground(Color.GRAY);
            }
        }

        if (botones[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + 0].getBackground() == obtenerColor(combinacionSecreta[0])
                && botones[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + 1].getBackground() == obtenerColor(combinacionSecreta[1])
                && botones[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + 2].getBackground() == obtenerColor(combinacionSecreta[2])
                && botones[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + 3].getBackground() == obtenerColor(combinacionSecreta[3])) {
            finDelJuego();
            JOptionPane.showMessageDialog(this, "¡Has acertado! Puntuacion: " + ((minutos * 60 + segundos) * 67));
            this.setVisible(false);
            MenuInicio menu = new MenuInicio();
            menu.setVisible(true);
        }
    }

    private void finDelJuego() {
        contar = false;
        if (posicionY == NUM_BOTONES_LARGO - 1) {
            for (int i = 0; i < 4; i++) {
                if (botones[NUM_BOTONES_LARGO - 1][posicionX - NUM_BOTONES_ANCHO + 1 + i].getBackground() == obtenerColor(combinacionSecreta[i])) {
                    pistas[NUM_BOTONES_LARGO - 1][posicionX - NUM_BOTONES_ANCHO + 1 + i].setBackground(Color.GREEN);
                } else {
                    pistas[NUM_BOTONES_LARGO - 1][posicionX - NUM_BOTONES_ANCHO + 1 + i].setBackground(Color.GRAY);
                }
            }
        }

        botonRojo.setEnabled(false);
        botonVerde.setEnabled(false);
        botonAzul.setEnabled(false);
        botonAmarillo.setEnabled(false);
        botonIntroducir.setEnabled(false);
        botonRetroceder.setEnabled(false);
        
        
        combinacion1.setBackground(obtenerColor(combinacionSecreta[0]));
        combinacion2.setBackground(obtenerColor(combinacionSecreta[1]));
        combinacion3.setBackground(obtenerColor(combinacionSecreta[2]));
        combinacion4.setBackground(obtenerColor(combinacionSecreta[3]));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        botonRojo = new javax.swing.JButton();
        botonVerde = new javax.swing.JButton();
        botonAzul = new javax.swing.JButton();
        botonAmarillo = new javax.swing.JButton();
        botonRetroceder = new javax.swing.JButton();
        botonIntroducir = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        espacio1 = new javax.swing.JLabel();
        espacio2 = new javax.swing.JLabel();
        espacio3 = new javax.swing.JLabel();
        espacio4 = new javax.swing.JLabel();
        panelPistas1 = new javax.swing.JPanel();
        pista1 = new javax.swing.JLabel();
        pista2 = new javax.swing.JLabel();
        pista3 = new javax.swing.JLabel();
        pista4 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        espacio5 = new javax.swing.JLabel();
        espacio6 = new javax.swing.JLabel();
        espacio7 = new javax.swing.JLabel();
        espacio8 = new javax.swing.JLabel();
        panelPistas2 = new javax.swing.JPanel();
        pista5 = new javax.swing.JLabel();
        pista6 = new javax.swing.JLabel();
        pista7 = new javax.swing.JLabel();
        pista8 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        espacio9 = new javax.swing.JLabel();
        espacio10 = new javax.swing.JLabel();
        espacio11 = new javax.swing.JLabel();
        espacio12 = new javax.swing.JLabel();
        panelPistas3 = new javax.swing.JPanel();
        pista9 = new javax.swing.JLabel();
        pista10 = new javax.swing.JLabel();
        pista11 = new javax.swing.JLabel();
        pista12 = new javax.swing.JLabel();
        panel4 = new javax.swing.JPanel();
        espacio13 = new javax.swing.JLabel();
        espacio14 = new javax.swing.JLabel();
        espacio15 = new javax.swing.JLabel();
        panelPistas4 = new javax.swing.JPanel();
        pista13 = new javax.swing.JLabel();
        pista14 = new javax.swing.JLabel();
        pista15 = new javax.swing.JLabel();
        pista16 = new javax.swing.JLabel();
        espacio16 = new javax.swing.JLabel();
        panel5 = new javax.swing.JPanel();
        espacio17 = new javax.swing.JLabel();
        espacio18 = new javax.swing.JLabel();
        espacio19 = new javax.swing.JLabel();
        espacio20 = new javax.swing.JLabel();
        panelPistas5 = new javax.swing.JPanel();
        pista17 = new javax.swing.JLabel();
        pista18 = new javax.swing.JLabel();
        pista19 = new javax.swing.JLabel();
        pista20 = new javax.swing.JLabel();
        panel6 = new javax.swing.JPanel();
        espacio21 = new javax.swing.JLabel();
        espacio22 = new javax.swing.JLabel();
        espacio23 = new javax.swing.JLabel();
        espacio24 = new javax.swing.JLabel();
        panelPistas6 = new javax.swing.JPanel();
        pista21 = new javax.swing.JLabel();
        pista22 = new javax.swing.JLabel();
        pista23 = new javax.swing.JLabel();
        pista24 = new javax.swing.JLabel();
        panel7 = new javax.swing.JPanel();
        espacio25 = new javax.swing.JLabel();
        espacio26 = new javax.swing.JLabel();
        espacio27 = new javax.swing.JLabel();
        espacio28 = new javax.swing.JLabel();
        panelPistas7 = new javax.swing.JPanel();
        pista25 = new javax.swing.JLabel();
        pista26 = new javax.swing.JLabel();
        pista27 = new javax.swing.JLabel();
        pista28 = new javax.swing.JLabel();
        panel8 = new javax.swing.JPanel();
        espacio29 = new javax.swing.JLabel();
        espacio30 = new javax.swing.JLabel();
        espacio31 = new javax.swing.JLabel();
        espacio32 = new javax.swing.JLabel();
        panel9 = new javax.swing.JPanel();
        combinacion1 = new javax.swing.JLabel();
        combinacion2 = new javax.swing.JLabel();
        combinacion3 = new javax.swing.JLabel();
        combinacion4 = new javax.swing.JLabel();
        panelPistas8 = new javax.swing.JPanel();
        pista29 = new javax.swing.JLabel();
        pista30 = new javax.swing.JLabel();
        pista31 = new javax.swing.JLabel();
        pista32 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        campoTiempo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        campoIntentos = new javax.swing.JLabel();
        botonReinicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botonRojo.setBackground(new java.awt.Color(255, 0, 51));
        botonRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRojoActionPerformed(evt);
            }
        });

        botonVerde.setBackground(new java.awt.Color(51, 255, 0));
        botonVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerdeActionPerformed(evt);
            }
        });

        botonAzul.setBackground(new java.awt.Color(51, 51, 255));
        botonAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAzulActionPerformed(evt);
            }
        });

        botonAmarillo.setBackground(new java.awt.Color(255, 255, 0));
        botonAmarillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAmarilloActionPerformed(evt);
            }
        });

        botonRetroceder.setText("Retroceder");
        botonRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRetrocederActionPerformed(evt);
            }
        });

        botonIntroducir.setText("Introducir");
        botonIntroducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIntroducirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(botonRetroceder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addComponent(botonRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addComponent(botonAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(botonIntroducir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonRetroceder, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(botonIntroducir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        espacio1.setBackground(new java.awt.Color(238, 248, 251));
        espacio1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio1.setOpaque(true);

        espacio2.setBackground(new java.awt.Color(238, 248, 251));
        espacio2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio2.setOpaque(true);

        espacio3.setBackground(new java.awt.Color(238, 248, 251));
        espacio3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio3.setOpaque(true);

        espacio4.setBackground(new java.awt.Color(238, 248, 251));
        espacio4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio4.setOpaque(true);

        panelPistas1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista1.setBackground(new java.awt.Color(255, 255, 255));
        pista1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista1.setOpaque(true);

        pista2.setBackground(new java.awt.Color(255, 255, 255));
        pista2.setForeground(new java.awt.Color(51, 255, 51));
        pista2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista2.setOpaque(true);

        pista3.setBackground(new java.awt.Color(255, 255, 255));
        pista3.setForeground(new java.awt.Color(51, 255, 51));
        pista3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista3.setOpaque(true);

        pista4.setBackground(new java.awt.Color(255, 255, 255));
        pista4.setForeground(new java.awt.Color(51, 255, 51));
        pista4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista4.setOpaque(true);

        javax.swing.GroupLayout panelPistas1Layout = new javax.swing.GroupLayout(panelPistas1);
        panelPistas1.setLayout(panelPistas1Layout);
        panelPistas1Layout.setHorizontalGroup(
            panelPistas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas1Layout.createSequentialGroup()
                        .addComponent(pista1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas1Layout.createSequentialGroup()
                        .addComponent(pista3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas1Layout.setVerticalGroup(
            panelPistas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(panelPistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio5.setBackground(new java.awt.Color(238, 248, 251));
        espacio5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio5.setOpaque(true);

        espacio6.setBackground(new java.awt.Color(238, 248, 251));
        espacio6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio6.setOpaque(true);

        espacio7.setBackground(new java.awt.Color(238, 248, 251));
        espacio7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio7.setOpaque(true);

        espacio8.setBackground(new java.awt.Color(238, 248, 251));
        espacio8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio8.setOpaque(true);

        panelPistas2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista5.setBackground(new java.awt.Color(255, 255, 255));
        pista5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista5.setOpaque(true);

        pista6.setBackground(new java.awt.Color(255, 255, 255));
        pista6.setForeground(new java.awt.Color(51, 255, 51));
        pista6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista6.setOpaque(true);

        pista7.setBackground(new java.awt.Color(255, 255, 255));
        pista7.setForeground(new java.awt.Color(51, 255, 51));
        pista7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista7.setOpaque(true);

        pista8.setBackground(new java.awt.Color(255, 255, 255));
        pista8.setForeground(new java.awt.Color(51, 255, 51));
        pista8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista8.setOpaque(true);

        javax.swing.GroupLayout panelPistas2Layout = new javax.swing.GroupLayout(panelPistas2);
        panelPistas2.setLayout(panelPistas2Layout);
        panelPistas2Layout.setHorizontalGroup(
            panelPistas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas2Layout.createSequentialGroup()
                        .addComponent(pista5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas2Layout.createSequentialGroup()
                        .addComponent(pista7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas2Layout.setVerticalGroup(
            panelPistas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(panelPistas2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio9.setBackground(new java.awt.Color(238, 248, 251));
        espacio9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio9.setOpaque(true);

        espacio10.setBackground(new java.awt.Color(238, 248, 251));
        espacio10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio10.setOpaque(true);

        espacio11.setBackground(new java.awt.Color(238, 248, 251));
        espacio11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio11.setOpaque(true);

        espacio12.setBackground(new java.awt.Color(238, 248, 251));
        espacio12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio12.setOpaque(true);

        panelPistas3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista9.setBackground(new java.awt.Color(255, 255, 255));
        pista9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista9.setOpaque(true);

        pista10.setBackground(new java.awt.Color(255, 255, 255));
        pista10.setForeground(new java.awt.Color(51, 255, 51));
        pista10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista10.setOpaque(true);

        pista11.setBackground(new java.awt.Color(255, 255, 255));
        pista11.setForeground(new java.awt.Color(51, 255, 51));
        pista11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista11.setOpaque(true);

        pista12.setBackground(new java.awt.Color(255, 255, 255));
        pista12.setForeground(new java.awt.Color(51, 255, 51));
        pista12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista12.setOpaque(true);

        javax.swing.GroupLayout panelPistas3Layout = new javax.swing.GroupLayout(panelPistas3);
        panelPistas3.setLayout(panelPistas3Layout);
        panelPistas3Layout.setHorizontalGroup(
            panelPistas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas3Layout.createSequentialGroup()
                        .addComponent(pista9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas3Layout.createSequentialGroup()
                        .addComponent(pista11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas3Layout.setVerticalGroup(
            panelPistas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addComponent(panelPistas3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio13.setBackground(new java.awt.Color(238, 248, 251));
        espacio13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio13.setOpaque(true);

        espacio14.setBackground(new java.awt.Color(238, 248, 251));
        espacio14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio14.setOpaque(true);

        espacio15.setBackground(new java.awt.Color(238, 248, 251));
        espacio15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio15.setOpaque(true);

        panelPistas4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista13.setBackground(new java.awt.Color(255, 255, 255));
        pista13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista13.setOpaque(true);

        pista14.setBackground(new java.awt.Color(255, 255, 255));
        pista14.setForeground(new java.awt.Color(51, 255, 51));
        pista14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista14.setOpaque(true);

        pista15.setBackground(new java.awt.Color(255, 255, 255));
        pista15.setForeground(new java.awt.Color(51, 255, 51));
        pista15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista15.setOpaque(true);

        pista16.setBackground(new java.awt.Color(255, 255, 255));
        pista16.setForeground(new java.awt.Color(51, 255, 51));
        pista16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista16.setOpaque(true);

        javax.swing.GroupLayout panelPistas4Layout = new javax.swing.GroupLayout(panelPistas4);
        panelPistas4.setLayout(panelPistas4Layout);
        panelPistas4Layout.setHorizontalGroup(
            panelPistas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas4Layout.createSequentialGroup()
                        .addComponent(pista13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas4Layout.createSequentialGroup()
                        .addComponent(pista15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas4Layout.setVerticalGroup(
            panelPistas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        espacio16.setBackground(new java.awt.Color(238, 248, 251));
        espacio16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio16.setOpaque(true);

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addComponent(panelPistas4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio17.setBackground(new java.awt.Color(238, 248, 251));
        espacio17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio17.setOpaque(true);

        espacio18.setBackground(new java.awt.Color(238, 248, 251));
        espacio18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio18.setOpaque(true);

        espacio19.setBackground(new java.awt.Color(238, 248, 251));
        espacio19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio19.setOpaque(true);

        espacio20.setBackground(new java.awt.Color(238, 248, 251));
        espacio20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio20.setOpaque(true);

        panelPistas5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista17.setBackground(new java.awt.Color(255, 255, 255));
        pista17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista17.setOpaque(true);

        pista18.setBackground(new java.awt.Color(255, 255, 255));
        pista18.setForeground(new java.awt.Color(51, 255, 51));
        pista18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista18.setOpaque(true);

        pista19.setBackground(new java.awt.Color(255, 255, 255));
        pista19.setForeground(new java.awt.Color(51, 255, 51));
        pista19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista19.setOpaque(true);

        pista20.setBackground(new java.awt.Color(255, 255, 255));
        pista20.setForeground(new java.awt.Color(51, 255, 51));
        pista20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista20.setOpaque(true);

        javax.swing.GroupLayout panelPistas5Layout = new javax.swing.GroupLayout(panelPistas5);
        panelPistas5.setLayout(panelPistas5Layout);
        panelPistas5Layout.setHorizontalGroup(
            panelPistas5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas5Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas5Layout.createSequentialGroup()
                        .addComponent(pista17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas5Layout.createSequentialGroup()
                        .addComponent(pista19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas5Layout.setVerticalGroup(
            panelPistas5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas5Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addComponent(panelPistas5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio21.setBackground(new java.awt.Color(238, 248, 251));
        espacio21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio21.setOpaque(true);

        espacio22.setBackground(new java.awt.Color(238, 248, 251));
        espacio22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio22.setOpaque(true);

        espacio23.setBackground(new java.awt.Color(238, 248, 251));
        espacio23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio23.setOpaque(true);

        espacio24.setBackground(new java.awt.Color(238, 248, 251));
        espacio24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio24.setOpaque(true);

        panelPistas6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista21.setBackground(new java.awt.Color(255, 255, 255));
        pista21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista21.setOpaque(true);

        pista22.setBackground(new java.awt.Color(255, 255, 255));
        pista22.setForeground(new java.awt.Color(51, 255, 51));
        pista22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista22.setOpaque(true);

        pista23.setBackground(new java.awt.Color(255, 255, 255));
        pista23.setForeground(new java.awt.Color(51, 255, 51));
        pista23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista23.setOpaque(true);

        pista24.setBackground(new java.awt.Color(255, 255, 255));
        pista24.setForeground(new java.awt.Color(51, 255, 51));
        pista24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista24.setOpaque(true);

        javax.swing.GroupLayout panelPistas6Layout = new javax.swing.GroupLayout(panelPistas6);
        panelPistas6.setLayout(panelPistas6Layout);
        panelPistas6Layout.setHorizontalGroup(
            panelPistas6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas6Layout.createSequentialGroup()
                        .addComponent(pista21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas6Layout.createSequentialGroup()
                        .addComponent(pista23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas6Layout.setVerticalGroup(
            panelPistas6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista23, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addComponent(panelPistas6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio25.setBackground(new java.awt.Color(238, 248, 251));
        espacio25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio25.setOpaque(true);

        espacio26.setBackground(new java.awt.Color(238, 248, 251));
        espacio26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio26.setOpaque(true);

        espacio27.setBackground(new java.awt.Color(238, 248, 251));
        espacio27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio27.setOpaque(true);

        espacio28.setBackground(new java.awt.Color(238, 248, 251));
        espacio28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio28.setOpaque(true);

        panelPistas7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista25.setBackground(new java.awt.Color(255, 255, 255));
        pista25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista25.setOpaque(true);

        pista26.setBackground(new java.awt.Color(255, 255, 255));
        pista26.setForeground(new java.awt.Color(51, 255, 51));
        pista26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista26.setOpaque(true);

        pista27.setBackground(new java.awt.Color(255, 255, 255));
        pista27.setForeground(new java.awt.Color(51, 255, 51));
        pista27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista27.setOpaque(true);

        pista28.setBackground(new java.awt.Color(255, 255, 255));
        pista28.setForeground(new java.awt.Color(51, 255, 51));
        pista28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista28.setOpaque(true);

        javax.swing.GroupLayout panelPistas7Layout = new javax.swing.GroupLayout(panelPistas7);
        panelPistas7.setLayout(panelPistas7Layout);
        panelPistas7Layout.setHorizontalGroup(
            panelPistas7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas7Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas7Layout.createSequentialGroup()
                        .addComponent(pista25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista26, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas7Layout.createSequentialGroup()
                        .addComponent(pista27, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas7Layout.setVerticalGroup(
            panelPistas7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas7Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista26, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista27, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista28, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addComponent(panelPistas7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio29.setBackground(new java.awt.Color(238, 248, 251));
        espacio29.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio29.setOpaque(true);

        espacio30.setBackground(new java.awt.Color(238, 248, 251));
        espacio30.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio30.setOpaque(true);

        espacio31.setBackground(new java.awt.Color(238, 248, 251));
        espacio31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio31.setOpaque(true);

        espacio32.setBackground(new java.awt.Color(238, 248, 251));
        espacio32.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio32.setOpaque(true);

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel8Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(espacio30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        combinacion1.setBackground(new java.awt.Color(204, 204, 204));
        combinacion1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combinacion1.setOpaque(true);

        combinacion2.setBackground(new java.awt.Color(204, 204, 204));
        combinacion2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combinacion2.setOpaque(true);

        combinacion3.setBackground(new java.awt.Color(204, 204, 204));
        combinacion3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combinacion3.setOpaque(true);

        combinacion4.setBackground(new java.awt.Color(204, 204, 204));
        combinacion4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combinacion4.setOpaque(true);

        javax.swing.GroupLayout panel9Layout = new javax.swing.GroupLayout(panel9);
        panel9.setLayout(panel9Layout);
        panel9Layout.setHorizontalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(combinacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combinacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combinacion3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combinacion4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel9Layout.setVerticalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combinacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combinacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(combinacion3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(combinacion4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        panelPistas8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista29.setBackground(new java.awt.Color(255, 255, 255));
        pista29.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista29.setOpaque(true);

        pista30.setBackground(new java.awt.Color(255, 255, 255));
        pista30.setForeground(new java.awt.Color(51, 255, 51));
        pista30.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista30.setOpaque(true);

        pista31.setBackground(new java.awt.Color(255, 255, 255));
        pista31.setForeground(new java.awt.Color(51, 255, 51));
        pista31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista31.setOpaque(true);

        pista32.setBackground(new java.awt.Color(255, 255, 255));
        pista32.setForeground(new java.awt.Color(51, 255, 51));
        pista32.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista32.setOpaque(true);

        javax.swing.GroupLayout panelPistas8Layout = new javax.swing.GroupLayout(panelPistas8);
        panelPistas8.setLayout(panelPistas8Layout);
        panelPistas8Layout.setHorizontalGroup(
            panelPistas8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas8Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas8Layout.createSequentialGroup()
                        .addComponent(pista29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista30, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas8Layout.createSequentialGroup()
                        .addComponent(pista31, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista32, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas8Layout.setVerticalGroup(
            panelPistas8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas8Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista29, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista30, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista31, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista32, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(panelPistas8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelPistas8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tiempo"));

        campoTiempo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        campoTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        campoTiempo.setText("0:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(campoTiempo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Intentos"));

        campoIntentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        campoIntentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        campoIntentos.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(campoIntentos, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(campoIntentos)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        botonReinicio.setIcon(new javax.swing.ImageIcon("C:\\Users\\Juan Manuel\\Google Drive\\Mastermind sync\\res\\btnReinicio.png")); // NOI18N
        botonReinicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReinicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonReinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(botonReinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRojoActionPerformed
        if (posicionX < NUM_BOTONES_ANCHO - 1) {
            posicionX++;
        }

        botones[posicionY][posicionX].setBackground(Color.RED);
    }//GEN-LAST:event_botonRojoActionPerformed

    private void botonVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerdeActionPerformed
        if (posicionX < NUM_BOTONES_ANCHO - 1) {
            posicionX++;
        }

        botones[posicionY][posicionX].setBackground(Color.GREEN);
    }//GEN-LAST:event_botonVerdeActionPerformed

    private void botonAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAzulActionPerformed
        if (posicionX < NUM_BOTONES_ANCHO - 1) {
            posicionX++;
        }

        botones[posicionY][posicionX].setBackground(Color.BLUE);
    }//GEN-LAST:event_botonAzulActionPerformed

    private void botonAmarilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAmarilloActionPerformed
        if (posicionX < NUM_BOTONES_ANCHO - 1) {
            posicionX++;
        }

        botones[posicionY][posicionX].setBackground(Color.YELLOW);
    }//GEN-LAST:event_botonAmarilloActionPerformed

    private void botonRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRetrocederActionPerformed

        if (posicionX >= 0) {
            botones[posicionY][posicionX].setBackground(new Color(238, 248, 251));
            posicionX--;
        }
    }//GEN-LAST:event_botonRetrocederActionPerformed

    private void botonIntroducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIntroducirActionPerformed
        if (posicionX == NUM_BOTONES_ANCHO - 1 && posicionY < NUM_BOTONES_LARGO - 1) {
            campoIntentos.setText(String.valueOf(++intentos));
            comprobarCasilla();
            posicionY++;
            posicionX = -1;
        }
        if (posicionX == NUM_BOTONES_ANCHO - 1 && posicionY == NUM_BOTONES_LARGO - 1) {
            finDelJuego();
            campoIntentos.setText(String.valueOf(++intentos));
            JOptionPane.showMessageDialog(this, "Has perdido :(");
            this.setVisible(false);
            MenuInicio menu = new MenuInicio();
            menu.setVisible(true);
        }

    }//GEN-LAST:event_botonIntroducirActionPerformed

    private void botonReinicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReinicioActionPerformed
        generarClave();
        
        combinacion1.setBackground(new Color(238, 248, 251));
        combinacion2.setBackground(new Color(238, 248, 251));
        combinacion3.setBackground(new Color(238, 248, 251));
        combinacion4.setBackground(new Color(238, 248, 251));
        
        botonRojo.setEnabled(true);
        botonVerde.setEnabled(true);
        botonAzul.setEnabled(true);
        botonAmarillo.setEnabled(true);
        botonIntroducir.setEnabled(true);
        botonRetroceder.setEnabled(true);
        
        posicionX = -1;
        posicionY = 0;
        intentos = 0;
        minutos = 0;
        segundos = 25;

        NumberFormat formato = NumberFormat.getInstance();
        formato.setMinimumIntegerDigits(2);
        campoTiempo.setText(minutos + ":" + formato.format(segundos));
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 4; x++) {
                botones[y][x].setBackground(new Color(238, 248, 251));
                pistas[y][x].setBackground(new Color(238, 248, 251));
            }
        }
    }//GEN-LAST:event_botonReinicioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAmarillo;
    private javax.swing.JButton botonAzul;
    private javax.swing.JButton botonIntroducir;
    private javax.swing.JButton botonReinicio;
    private javax.swing.JButton botonRetroceder;
    private javax.swing.JButton botonRojo;
    private javax.swing.JButton botonVerde;
    private javax.swing.JLabel campoIntentos;
    private javax.swing.JLabel campoTiempo;
    private javax.swing.JLabel combinacion1;
    private javax.swing.JLabel combinacion2;
    private javax.swing.JLabel combinacion3;
    private javax.swing.JLabel combinacion4;
    private javax.swing.JLabel espacio1;
    private javax.swing.JLabel espacio10;
    private javax.swing.JLabel espacio11;
    private javax.swing.JLabel espacio12;
    private javax.swing.JLabel espacio13;
    private javax.swing.JLabel espacio14;
    private javax.swing.JLabel espacio15;
    private javax.swing.JLabel espacio16;
    private javax.swing.JLabel espacio17;
    private javax.swing.JLabel espacio18;
    private javax.swing.JLabel espacio19;
    private javax.swing.JLabel espacio2;
    private javax.swing.JLabel espacio20;
    private javax.swing.JLabel espacio21;
    private javax.swing.JLabel espacio22;
    private javax.swing.JLabel espacio23;
    private javax.swing.JLabel espacio24;
    private javax.swing.JLabel espacio25;
    private javax.swing.JLabel espacio26;
    private javax.swing.JLabel espacio27;
    private javax.swing.JLabel espacio28;
    private javax.swing.JLabel espacio29;
    private javax.swing.JLabel espacio3;
    private javax.swing.JLabel espacio30;
    private javax.swing.JLabel espacio31;
    private javax.swing.JLabel espacio32;
    private javax.swing.JLabel espacio4;
    private javax.swing.JLabel espacio5;
    private javax.swing.JLabel espacio6;
    private javax.swing.JLabel espacio7;
    private javax.swing.JLabel espacio8;
    private javax.swing.JLabel espacio9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JPanel panel9;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelPistas1;
    private javax.swing.JPanel panelPistas2;
    private javax.swing.JPanel panelPistas3;
    private javax.swing.JPanel panelPistas4;
    private javax.swing.JPanel panelPistas5;
    private javax.swing.JPanel panelPistas6;
    private javax.swing.JPanel panelPistas7;
    private javax.swing.JPanel panelPistas8;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JLabel pista1;
    private javax.swing.JLabel pista10;
    private javax.swing.JLabel pista11;
    private javax.swing.JLabel pista12;
    private javax.swing.JLabel pista13;
    private javax.swing.JLabel pista14;
    private javax.swing.JLabel pista15;
    private javax.swing.JLabel pista16;
    private javax.swing.JLabel pista17;
    private javax.swing.JLabel pista18;
    private javax.swing.JLabel pista19;
    private javax.swing.JLabel pista2;
    private javax.swing.JLabel pista20;
    private javax.swing.JLabel pista21;
    private javax.swing.JLabel pista22;
    private javax.swing.JLabel pista23;
    private javax.swing.JLabel pista24;
    private javax.swing.JLabel pista25;
    private javax.swing.JLabel pista26;
    private javax.swing.JLabel pista27;
    private javax.swing.JLabel pista28;
    private javax.swing.JLabel pista29;
    private javax.swing.JLabel pista3;
    private javax.swing.JLabel pista30;
    private javax.swing.JLabel pista31;
    private javax.swing.JLabel pista32;
    private javax.swing.JLabel pista4;
    private javax.swing.JLabel pista5;
    private javax.swing.JLabel pista6;
    private javax.swing.JLabel pista7;
    private javax.swing.JLabel pista8;
    private javax.swing.JLabel pista9;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (contar) {
            try {
                if (segundos > 0) {
                    segundos--;
                } else {
                    minutos--;
                    segundos = 59;
                }

                if (minutos == 0 && segundos == 0) {
                    finDelJuego();
                    JOptionPane.showMessageDialog(this, "Has perdido :(");
                    this.setVisible(false);
                    MenuInicio menu = new MenuInicio();
                    menu.setVisible(true);
                }
                NumberFormat formato = NumberFormat.getInstance();
                formato.setMinimumIntegerDigits(2);

                campoTiempo.setText(minutos + ":" + formato.format(segundos));

                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MastermindDificil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
