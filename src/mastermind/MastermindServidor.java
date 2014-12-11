/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import Socket.Servidor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Manuel
 */
public class MastermindServidor extends javax.swing.JFrame {

    /**
     * Número de botones que hay en posición horizontal.
     */
    final int NUM_BOTONES_ANCHO = 4;
    /**
     * Número de botones que hay en posición vertical.
     */
    final int NUM_BOTONES_LARGO = 8;

    private Servidor servidor;
    
    private boolean rojoUsado = false;
    private boolean verdeUsado = false;
    private boolean azulUsado = false;
    private boolean amarilloUsado = false;
    private char combinacionSecreta[] = new char[4];

    private int posicionY = 0;
    private int posicionX = -1;

    private final char COLOR_ROJO = 'R';
    private final char COLOR_VERDE = 'G';
    private final char COLOR_AZUL = 'B';
    private final char COLOR_AMARILLO = 'Y';

    private JLabel[][] botonesJugador1;
    private JLabel[][] pistasJugador1;
    private JLabel[][] botonesJugador2;
    private JLabel[][] pistasJugador2;

    public JLabel[][] getBotonesJugador1() {
        return botonesJugador1;
    }

    public JLabel[][] getBotonesJugador2() {
        return botonesJugador2;
    }
    
    public JButton getBotonRojo() {
        return botonRojo;
    }
    
    public JButton getBotonVerde() {
        return botonVerde;
    }

    public JButton getBotonAzul() {
        return botonAzul;
    }

    public JButton getBotonAmarillo() {
        return botonAmarillo;
    }
    
    public JButton getBotonIntroducir() {
        return botonIntroducir;
    }

    public JButton getBotonRetroceder() {
        return botonRetroceder;
    }

    public MastermindServidor() {
        initComponents();
        setTitle("Mastermind servidor");

        try {
            servidor = new Servidor(this);
            servidor.start();
        } catch (IOException ex) {
            Logger.getLogger(MastermindServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Inicializar componentes de la ventana.
        JLabel[][] botonesJugador1Init = {
            {espacio1, espacio2, espacio3, espacio4},
            {espacio5, espacio6, espacio7, espacio8},
            {espacio9, espacio10, espacio11, espacio12},
            {espacio13, espacio14, espacio15, espacio16},
            {espacio17, espacio18, espacio19, espacio20},
            {espacio21, espacio22, espacio23, espacio24},
            {espacio25, espacio26, espacio27, espacio28},
            {espacio29, espacio30, espacio31, espacio32}
        };
        
        JLabel[][] botonesJugador2Init = {
            {espacio33, espacio34, espacio35, espacio36},
            {espacio37, espacio38, espacio39, espacio40},
            {espacio41, espacio42, espacio43, espacio44},
            {espacio45, espacio46, espacio47, espacio48},
            {espacio49, espacio50, espacio51, espacio52},
            {espacio53, espacio54, espacio55, espacio56},
            {espacio57, espacio58, espacio59, espacio60},
            {espacio61, espacio62, espacio63, espacio64}
        };

        JLabel[][] pistasJugador1Init = {
            {pista1, pista2, pista3, pista4},
            {pista5, pista6, pista7, pista8},
            {pista9, pista10, pista11, pista12},
            {pista13, pista14, pista15, pista16},
            {pista17, pista18, pista19, pista20},
            {pista21, pista22, pista23, pista24},
            {pista25, pista26, pista27, pista28},
            {pista29, pista30, pista31, pista32}
        };
        
        JLabel[][] pistasJugador2Init = {
            {pista33, pista34, pista35, pista36},
            {pista37, pista38, pista39, pista40},
            {pista41, pista42, pista43, pista44},
            {pista45, pista46, pista47, pista48},
            {pista49, pista50, pista51, pista52},
            {pista53, pista54, pista55, pista56},
            {pista57, pista58, pista59, pista60},
            {pista61, pista62, pista63, pista64}
        };
        
        this.botonesJugador1 = botonesJugador1Init;
        this.pistasJugador1 = pistasJugador1Init;
        this.botonesJugador2 = botonesJugador2Init;
        this.pistasJugador2 = pistasJugador2Init;

        //Centrar ventana.
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Generar combinación secreta.
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
    
    private Color obtenerColor(char c){
        if (c == 'R')
        return Color.RED;
        else if (c == 'G')
        return Color.GREEN;
        else if (c == 'B')
        return Color.BLUE;
        else if (c == 'Y')
        return Color.YELLOW;
        
        return null;
    }
    
    private void comprobarCasilla() {
        
        for (int i = 0; i < 4; i++) {
            if(botonesJugador1[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + i].getBackground() == obtenerColor(combinacionSecreta[i]))
                pistasJugador1[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + i].setBackground(Color.GREEN);
            else 
                pistasJugador1[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + i].setBackground(Color.GRAY);
        }
        
         if(botonesJugador1[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + 0].getBackground() == obtenerColor(combinacionSecreta[0])
                    && botonesJugador1[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + 1].getBackground() == obtenerColor(combinacionSecreta[1])
                    && botonesJugador1[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + 2].getBackground() == obtenerColor(combinacionSecreta[2])
                    && botonesJugador1[posicionY][posicionX - NUM_BOTONES_ANCHO + 1 + 3].getBackground() == obtenerColor(combinacionSecreta[3])){
                finDelJuego("¡Has ganado!");
            }
    }
    
    private void enviar(String string) {
        try {
            servidor.enviar(string);
        } catch (IOException ex) {
            Logger.getLogger(MastermindCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void finDelJuego(String string){
        if(posicionY == NUM_BOTONES_LARGO - 1){
            for (int i = 0; i < 4; i++) {
                if(botonesJugador1[NUM_BOTONES_LARGO - 1][posicionX - NUM_BOTONES_ANCHO + 1 + i].getBackground() == obtenerColor(combinacionSecreta[i]))
                    pistasJugador1[NUM_BOTONES_LARGO - 1][posicionX - NUM_BOTONES_ANCHO + 1 + i].setBackground(Color.GREEN);
                else 
                    pistasJugador1[NUM_BOTONES_LARGO - 1][posicionX - NUM_BOTONES_ANCHO + 1 + i].setBackground(Color.GRAY);
            }
        }
        
        combinacion1.setBackground(obtenerColor(combinacionSecreta[0]));
        combinacion2.setBackground(obtenerColor(combinacionSecreta[1]));
        combinacion3.setBackground(obtenerColor(combinacionSecreta[2]));
        combinacion4.setBackground(obtenerColor(combinacionSecreta[3]));
        
        botonRojo.setEnabled(false);
        botonVerde.setEnabled(false);
        botonAzul.setEnabled(false);
        botonAmarillo.setEnabled(false);
        botonIntroducir.setEnabled(false);
        botonRetroceder.setEnabled(false);
        
        if(string.equalsIgnoreCase("Has perdido :("))
            enviar("finVictoria");
        else if(string.equalsIgnoreCase("¡Has ganado!"))
            enviar("finDerrota");
        
        JOptionPane.showMessageDialog(this, string);
    }
   
    private void bonusMode() {

        Random random = new Random();

        for (int y = 0; y < NUM_BOTONES_LARGO; y++) {
            for (int x = 0; x < NUM_BOTONES_ANCHO; x++) {

                switch (random.nextInt(4)) {
                    case 0:
                        botonesJugador1[y][x].setBackground(Color.red);
                        break;
                    case 1:
                        botonesJugador1[y][x].setBackground(Color.green);
                        break;
                    case 2:
                        botonesJugador1[y][x].setBackground(Color.blue);
                        break;
                    case 3:
                        botonesJugador1[y][x].setBackground(Color.yellow);
                        break;
                }

            }
        }
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
        panelPrincipal1 = new javax.swing.JPanel();
        panel10 = new javax.swing.JPanel();
        espacio33 = new javax.swing.JLabel();
        espacio34 = new javax.swing.JLabel();
        espacio35 = new javax.swing.JLabel();
        espacio36 = new javax.swing.JLabel();
        panelPistas9 = new javax.swing.JPanel();
        pista33 = new javax.swing.JLabel();
        pista34 = new javax.swing.JLabel();
        pista35 = new javax.swing.JLabel();
        pista36 = new javax.swing.JLabel();
        panel11 = new javax.swing.JPanel();
        espacio37 = new javax.swing.JLabel();
        espacio38 = new javax.swing.JLabel();
        espacio39 = new javax.swing.JLabel();
        espacio40 = new javax.swing.JLabel();
        panelPistas10 = new javax.swing.JPanel();
        pista37 = new javax.swing.JLabel();
        pista38 = new javax.swing.JLabel();
        pista39 = new javax.swing.JLabel();
        pista40 = new javax.swing.JLabel();
        panel12 = new javax.swing.JPanel();
        espacio41 = new javax.swing.JLabel();
        espacio42 = new javax.swing.JLabel();
        espacio43 = new javax.swing.JLabel();
        espacio44 = new javax.swing.JLabel();
        panelPistas11 = new javax.swing.JPanel();
        pista41 = new javax.swing.JLabel();
        pista42 = new javax.swing.JLabel();
        pista43 = new javax.swing.JLabel();
        pista44 = new javax.swing.JLabel();
        panel13 = new javax.swing.JPanel();
        espacio45 = new javax.swing.JLabel();
        espacio46 = new javax.swing.JLabel();
        espacio47 = new javax.swing.JLabel();
        panelPistas12 = new javax.swing.JPanel();
        pista45 = new javax.swing.JLabel();
        pista46 = new javax.swing.JLabel();
        pista47 = new javax.swing.JLabel();
        pista48 = new javax.swing.JLabel();
        espacio48 = new javax.swing.JLabel();
        panel14 = new javax.swing.JPanel();
        espacio49 = new javax.swing.JLabel();
        espacio50 = new javax.swing.JLabel();
        espacio51 = new javax.swing.JLabel();
        espacio52 = new javax.swing.JLabel();
        panelPistas13 = new javax.swing.JPanel();
        pista49 = new javax.swing.JLabel();
        pista50 = new javax.swing.JLabel();
        pista51 = new javax.swing.JLabel();
        pista52 = new javax.swing.JLabel();
        panel15 = new javax.swing.JPanel();
        espacio53 = new javax.swing.JLabel();
        espacio54 = new javax.swing.JLabel();
        espacio55 = new javax.swing.JLabel();
        espacio56 = new javax.swing.JLabel();
        panelPistas14 = new javax.swing.JPanel();
        pista53 = new javax.swing.JLabel();
        pista54 = new javax.swing.JLabel();
        pista55 = new javax.swing.JLabel();
        pista56 = new javax.swing.JLabel();
        panel16 = new javax.swing.JPanel();
        espacio57 = new javax.swing.JLabel();
        espacio58 = new javax.swing.JLabel();
        espacio59 = new javax.swing.JLabel();
        espacio60 = new javax.swing.JLabel();
        panelPistas15 = new javax.swing.JPanel();
        pista57 = new javax.swing.JLabel();
        pista58 = new javax.swing.JLabel();
        pista59 = new javax.swing.JLabel();
        pista60 = new javax.swing.JLabel();
        panel17 = new javax.swing.JPanel();
        espacio61 = new javax.swing.JLabel();
        espacio62 = new javax.swing.JLabel();
        espacio63 = new javax.swing.JLabel();
        espacio64 = new javax.swing.JLabel();
        panel18 = new javax.swing.JPanel();
        combinacion5 = new javax.swing.JLabel();
        combinacion6 = new javax.swing.JLabel();
        combinacion7 = new javax.swing.JLabel();
        combinacion8 = new javax.swing.JLabel();
        panelPistas16 = new javax.swing.JPanel();
        pista61 = new javax.swing.JLabel();
        pista62 = new javax.swing.JLabel();
        pista63 = new javax.swing.JLabel();
        pista64 = new javax.swing.JLabel();
        panelMenu1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

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

        panelPrincipal1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        espacio33.setBackground(new java.awt.Color(238, 248, 251));
        espacio33.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio33.setOpaque(true);

        espacio34.setBackground(new java.awt.Color(238, 248, 251));
        espacio34.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio34.setOpaque(true);

        espacio35.setBackground(new java.awt.Color(238, 248, 251));
        espacio35.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio35.setOpaque(true);

        espacio36.setBackground(new java.awt.Color(238, 248, 251));
        espacio36.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio36.setOpaque(true);

        panelPistas9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista33.setBackground(new java.awt.Color(204, 204, 204));
        pista33.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista33.setOpaque(true);

        pista34.setBackground(new java.awt.Color(204, 204, 204));
        pista34.setForeground(new java.awt.Color(51, 255, 51));
        pista34.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista34.setOpaque(true);

        pista35.setBackground(new java.awt.Color(204, 204, 204));
        pista35.setForeground(new java.awt.Color(51, 255, 51));
        pista35.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista35.setOpaque(true);

        pista36.setBackground(new java.awt.Color(204, 204, 204));
        pista36.setForeground(new java.awt.Color(51, 255, 51));
        pista36.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista36.setOpaque(true);

        javax.swing.GroupLayout panelPistas9Layout = new javax.swing.GroupLayout(panelPistas9);
        panelPistas9.setLayout(panelPistas9Layout);
        panelPistas9Layout.setHorizontalGroup(
            panelPistas9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas9Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas9Layout.createSequentialGroup()
                        .addComponent(pista33, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista34, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas9Layout.createSequentialGroup()
                        .addComponent(pista35, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista36, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas9Layout.setVerticalGroup(
            panelPistas9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas9Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista33, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista34, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista35, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista36, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel10Layout.createSequentialGroup()
                .addComponent(panelPistas9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio37.setBackground(new java.awt.Color(238, 248, 251));
        espacio37.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio37.setOpaque(true);

        espacio38.setBackground(new java.awt.Color(238, 248, 251));
        espacio38.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio38.setOpaque(true);

        espacio39.setBackground(new java.awt.Color(238, 248, 251));
        espacio39.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio39.setOpaque(true);

        espacio40.setBackground(new java.awt.Color(238, 248, 251));
        espacio40.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio40.setOpaque(true);

        panelPistas10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista37.setBackground(new java.awt.Color(204, 204, 204));
        pista37.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista37.setOpaque(true);

        pista38.setBackground(new java.awt.Color(204, 204, 204));
        pista38.setForeground(new java.awt.Color(51, 255, 51));
        pista38.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista38.setOpaque(true);

        pista39.setBackground(new java.awt.Color(204, 204, 204));
        pista39.setForeground(new java.awt.Color(51, 255, 51));
        pista39.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista39.setOpaque(true);

        pista40.setBackground(new java.awt.Color(204, 204, 204));
        pista40.setForeground(new java.awt.Color(51, 255, 51));
        pista40.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista40.setOpaque(true);

        javax.swing.GroupLayout panelPistas10Layout = new javax.swing.GroupLayout(panelPistas10);
        panelPistas10.setLayout(panelPistas10Layout);
        panelPistas10Layout.setHorizontalGroup(
            panelPistas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas10Layout.createSequentialGroup()
                        .addComponent(pista37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista38, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas10Layout.createSequentialGroup()
                        .addComponent(pista39, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista40, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas10Layout.setVerticalGroup(
            panelPistas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista38, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista39, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista40, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel11Layout.createSequentialGroup()
                .addComponent(panelPistas10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio41.setBackground(new java.awt.Color(238, 248, 251));
        espacio41.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio41.setOpaque(true);

        espacio42.setBackground(new java.awt.Color(238, 248, 251));
        espacio42.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio42.setOpaque(true);

        espacio43.setBackground(new java.awt.Color(238, 248, 251));
        espacio43.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio43.setOpaque(true);

        espacio44.setBackground(new java.awt.Color(238, 248, 251));
        espacio44.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio44.setOpaque(true);

        panelPistas11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista41.setBackground(new java.awt.Color(204, 204, 204));
        pista41.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista41.setOpaque(true);

        pista42.setBackground(new java.awt.Color(204, 204, 204));
        pista42.setForeground(new java.awt.Color(51, 255, 51));
        pista42.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista42.setOpaque(true);

        pista43.setBackground(new java.awt.Color(204, 204, 204));
        pista43.setForeground(new java.awt.Color(51, 255, 51));
        pista43.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista43.setOpaque(true);

        pista44.setBackground(new java.awt.Color(204, 204, 204));
        pista44.setForeground(new java.awt.Color(51, 255, 51));
        pista44.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista44.setOpaque(true);

        javax.swing.GroupLayout panelPistas11Layout = new javax.swing.GroupLayout(panelPistas11);
        panelPistas11.setLayout(panelPistas11Layout);
        panelPistas11Layout.setHorizontalGroup(
            panelPistas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas11Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas11Layout.createSequentialGroup()
                        .addComponent(pista41, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista42, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas11Layout.createSequentialGroup()
                        .addComponent(pista43, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista44, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas11Layout.setVerticalGroup(
            panelPistas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas11Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista41, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista42, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista43, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista44, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel12Layout.createSequentialGroup()
                .addComponent(panelPistas11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio43, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio45.setBackground(new java.awt.Color(238, 248, 251));
        espacio45.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio45.setOpaque(true);

        espacio46.setBackground(new java.awt.Color(238, 248, 251));
        espacio46.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio46.setOpaque(true);

        espacio47.setBackground(new java.awt.Color(238, 248, 251));
        espacio47.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio47.setOpaque(true);

        panelPistas12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista45.setBackground(new java.awt.Color(204, 204, 204));
        pista45.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista45.setOpaque(true);

        pista46.setBackground(new java.awt.Color(204, 204, 204));
        pista46.setForeground(new java.awt.Color(51, 255, 51));
        pista46.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista46.setOpaque(true);

        pista47.setBackground(new java.awt.Color(204, 204, 204));
        pista47.setForeground(new java.awt.Color(51, 255, 51));
        pista47.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista47.setOpaque(true);

        pista48.setBackground(new java.awt.Color(204, 204, 204));
        pista48.setForeground(new java.awt.Color(51, 255, 51));
        pista48.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista48.setOpaque(true);

        javax.swing.GroupLayout panelPistas12Layout = new javax.swing.GroupLayout(panelPistas12);
        panelPistas12.setLayout(panelPistas12Layout);
        panelPistas12Layout.setHorizontalGroup(
            panelPistas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas12Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas12Layout.createSequentialGroup()
                        .addComponent(pista45, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista46, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas12Layout.createSequentialGroup()
                        .addComponent(pista47, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista48, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas12Layout.setVerticalGroup(
            panelPistas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas12Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista45, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista46, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista47, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista48, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        espacio48.setBackground(new java.awt.Color(238, 248, 251));
        espacio48.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio48.setOpaque(true);

        javax.swing.GroupLayout panel13Layout = new javax.swing.GroupLayout(panel13);
        panel13.setLayout(panel13Layout);
        panel13Layout.setHorizontalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel13Layout.createSequentialGroup()
                .addComponent(panelPistas12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel13Layout.setVerticalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio49.setBackground(new java.awt.Color(238, 248, 251));
        espacio49.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio49.setOpaque(true);

        espacio50.setBackground(new java.awt.Color(238, 248, 251));
        espacio50.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio50.setOpaque(true);

        espacio51.setBackground(new java.awt.Color(238, 248, 251));
        espacio51.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio51.setOpaque(true);

        espacio52.setBackground(new java.awt.Color(238, 248, 251));
        espacio52.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio52.setOpaque(true);

        panelPistas13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista49.setBackground(new java.awt.Color(204, 204, 204));
        pista49.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista49.setOpaque(true);

        pista50.setBackground(new java.awt.Color(204, 204, 204));
        pista50.setForeground(new java.awt.Color(51, 255, 51));
        pista50.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista50.setOpaque(true);

        pista51.setBackground(new java.awt.Color(204, 204, 204));
        pista51.setForeground(new java.awt.Color(51, 255, 51));
        pista51.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista51.setOpaque(true);

        pista52.setBackground(new java.awt.Color(204, 204, 204));
        pista52.setForeground(new java.awt.Color(51, 255, 51));
        pista52.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista52.setOpaque(true);

        javax.swing.GroupLayout panelPistas13Layout = new javax.swing.GroupLayout(panelPistas13);
        panelPistas13.setLayout(panelPistas13Layout);
        panelPistas13Layout.setHorizontalGroup(
            panelPistas13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas13Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas13Layout.createSequentialGroup()
                        .addComponent(pista49, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista50, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas13Layout.createSequentialGroup()
                        .addComponent(pista51, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista52, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas13Layout.setVerticalGroup(
            panelPistas13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas13Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista49, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista50, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista51, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista52, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel14Layout = new javax.swing.GroupLayout(panel14);
        panel14.setLayout(panel14Layout);
        panel14Layout.setHorizontalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel14Layout.createSequentialGroup()
                .addComponent(panelPistas13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel14Layout.setVerticalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio53.setBackground(new java.awt.Color(238, 248, 251));
        espacio53.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio53.setOpaque(true);

        espacio54.setBackground(new java.awt.Color(238, 248, 251));
        espacio54.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio54.setOpaque(true);

        espacio55.setBackground(new java.awt.Color(238, 248, 251));
        espacio55.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio55.setOpaque(true);

        espacio56.setBackground(new java.awt.Color(238, 248, 251));
        espacio56.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio56.setOpaque(true);

        panelPistas14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista53.setBackground(new java.awt.Color(204, 204, 204));
        pista53.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista53.setOpaque(true);

        pista54.setBackground(new java.awt.Color(204, 204, 204));
        pista54.setForeground(new java.awt.Color(51, 255, 51));
        pista54.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista54.setOpaque(true);

        pista55.setBackground(new java.awt.Color(204, 204, 204));
        pista55.setForeground(new java.awt.Color(51, 255, 51));
        pista55.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista55.setOpaque(true);

        pista56.setBackground(new java.awt.Color(204, 204, 204));
        pista56.setForeground(new java.awt.Color(51, 255, 51));
        pista56.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista56.setOpaque(true);

        javax.swing.GroupLayout panelPistas14Layout = new javax.swing.GroupLayout(panelPistas14);
        panelPistas14.setLayout(panelPistas14Layout);
        panelPistas14Layout.setHorizontalGroup(
            panelPistas14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas14Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas14Layout.createSequentialGroup()
                        .addComponent(pista53, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista54, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas14Layout.createSequentialGroup()
                        .addComponent(pista55, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista56, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas14Layout.setVerticalGroup(
            panelPistas14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas14Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista53, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista54, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista55, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista56, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel15Layout = new javax.swing.GroupLayout(panel15);
        panel15.setLayout(panel15Layout);
        panel15Layout.setHorizontalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel15Layout.createSequentialGroup()
                .addComponent(panelPistas14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel15Layout.setVerticalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio57.setBackground(new java.awt.Color(238, 248, 251));
        espacio57.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio57.setOpaque(true);

        espacio58.setBackground(new java.awt.Color(238, 248, 251));
        espacio58.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio58.setOpaque(true);

        espacio59.setBackground(new java.awt.Color(238, 248, 251));
        espacio59.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio59.setOpaque(true);

        espacio60.setBackground(new java.awt.Color(238, 248, 251));
        espacio60.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio60.setOpaque(true);

        panelPistas15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista57.setBackground(new java.awt.Color(204, 204, 204));
        pista57.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista57.setOpaque(true);

        pista58.setBackground(new java.awt.Color(204, 204, 204));
        pista58.setForeground(new java.awt.Color(51, 255, 51));
        pista58.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista58.setOpaque(true);

        pista59.setBackground(new java.awt.Color(204, 204, 204));
        pista59.setForeground(new java.awt.Color(51, 255, 51));
        pista59.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista59.setOpaque(true);

        pista60.setBackground(new java.awt.Color(204, 204, 204));
        pista60.setForeground(new java.awt.Color(51, 255, 51));
        pista60.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista60.setOpaque(true);

        javax.swing.GroupLayout panelPistas15Layout = new javax.swing.GroupLayout(panelPistas15);
        panelPistas15.setLayout(panelPistas15Layout);
        panelPistas15Layout.setHorizontalGroup(
            panelPistas15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas15Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas15Layout.createSequentialGroup()
                        .addComponent(pista57, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista58, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas15Layout.createSequentialGroup()
                        .addComponent(pista59, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista60, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas15Layout.setVerticalGroup(
            panelPistas15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas15Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista57, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista58, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista59, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista60, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel16Layout = new javax.swing.GroupLayout(panel16);
        panel16.setLayout(panel16Layout);
        panel16Layout.setHorizontalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel16Layout.createSequentialGroup()
                .addComponent(panelPistas15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel16Layout.setVerticalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPistas15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        espacio61.setBackground(new java.awt.Color(238, 248, 251));
        espacio61.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio61.setOpaque(true);

        espacio62.setBackground(new java.awt.Color(238, 248, 251));
        espacio62.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio62.setOpaque(true);

        espacio63.setBackground(new java.awt.Color(238, 248, 251));
        espacio63.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio63.setOpaque(true);

        espacio64.setBackground(new java.awt.Color(238, 248, 251));
        espacio64.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        espacio64.setOpaque(true);

        javax.swing.GroupLayout panel17Layout = new javax.swing.GroupLayout(panel17);
        panel17.setLayout(panel17Layout);
        panel17Layout.setHorizontalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(espacio61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espacio64, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel17Layout.setVerticalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel17Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(espacio62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacio61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(espacio63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(espacio64, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        combinacion5.setBackground(new java.awt.Color(204, 204, 204));
        combinacion5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combinacion5.setOpaque(true);

        combinacion6.setBackground(new java.awt.Color(204, 204, 204));
        combinacion6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combinacion6.setOpaque(true);

        combinacion7.setBackground(new java.awt.Color(204, 204, 204));
        combinacion7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combinacion7.setOpaque(true);

        combinacion8.setBackground(new java.awt.Color(204, 204, 204));
        combinacion8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combinacion8.setOpaque(true);

        javax.swing.GroupLayout panel18Layout = new javax.swing.GroupLayout(panel18);
        panel18.setLayout(panel18Layout);
        panel18Layout.setHorizontalGroup(
            panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(combinacion5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combinacion6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combinacion7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combinacion8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel18Layout.setVerticalGroup(
            panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combinacion6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combinacion5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(combinacion7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(combinacion8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        panelPistas16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pista61.setBackground(new java.awt.Color(204, 204, 204));
        pista61.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista61.setOpaque(true);

        pista62.setBackground(new java.awt.Color(204, 204, 204));
        pista62.setForeground(new java.awt.Color(51, 255, 51));
        pista62.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista62.setOpaque(true);

        pista63.setBackground(new java.awt.Color(204, 204, 204));
        pista63.setForeground(new java.awt.Color(51, 255, 51));
        pista63.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista63.setOpaque(true);

        pista64.setBackground(new java.awt.Color(204, 204, 204));
        pista64.setForeground(new java.awt.Color(51, 255, 51));
        pista64.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pista64.setOpaque(true);

        javax.swing.GroupLayout panelPistas16Layout = new javax.swing.GroupLayout(panelPistas16);
        panelPistas16.setLayout(panelPistas16Layout);
        panelPistas16Layout.setHorizontalGroup(
            panelPistas16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas16Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPistas16Layout.createSequentialGroup()
                        .addComponent(pista61, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista62, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPistas16Layout.createSequentialGroup()
                        .addComponent(pista63, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(pista64, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPistas16Layout.setVerticalGroup(
            panelPistas16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPistas16Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelPistas16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista61, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista62, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelPistas16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pista63, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pista64, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPrincipal1Layout = new javax.swing.GroupLayout(panelPrincipal1);
        panelPrincipal1.setLayout(panelPrincipal1Layout);
        panelPrincipal1Layout.setHorizontalGroup(
            panelPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipal1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelPrincipal1Layout.createSequentialGroup()
                        .addComponent(panelPistas16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );
        panelPrincipal1Layout.setVerticalGroup(
            panelPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipal1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelPistas16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelMenu1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Jugador 2");

        javax.swing.GroupLayout panelMenu1Layout = new javax.swing.GroupLayout(panelMenu1);
        panelMenu1.setLayout(panelMenu1Layout);
        panelMenu1Layout.setHorizontalGroup(
            panelMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenu1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenu1Layout.setVerticalGroup(
            panelMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenu1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelPrincipal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelPrincipal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRojoActionPerformed
        if (posicionX < NUM_BOTONES_ANCHO - 1)
            posicionX++;
            
        botonesJugador1[posicionY][posicionX].setBackground(Color.RED);
        enviar("btnRojo");
    }//GEN-LAST:event_botonRojoActionPerformed

    private void botonVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerdeActionPerformed
        if (posicionX < NUM_BOTONES_ANCHO - 1) 
            posicionX++;
        
        botonesJugador1[posicionY][posicionX].setBackground(Color.GREEN);
        enviar("btnVerde");
    }//GEN-LAST:event_botonVerdeActionPerformed

    private void botonAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAzulActionPerformed
        if (posicionX < NUM_BOTONES_ANCHO - 1) 
            posicionX++;
        
        botonesJugador1[posicionY][posicionX].setBackground(Color.BLUE);
        enviar("btnAzul");
    }//GEN-LAST:event_botonAzulActionPerformed

    private void botonAmarilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAmarilloActionPerformed
        if (posicionX < NUM_BOTONES_ANCHO - 1) 
            posicionX++;
        
        botonesJugador1[posicionY][posicionX].setBackground(Color.YELLOW);
        enviar("btnAmarillo");
    }//GEN-LAST:event_botonAmarilloActionPerformed

    private void botonRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRetrocederActionPerformed

         if (posicionX >= 0) {
            botonesJugador1[posicionY][posicionX].setBackground(new Color(238, 248, 251));
            posicionX--;
        }
        enviar("btnRetroceder");
    }//GEN-LAST:event_botonRetrocederActionPerformed

    private void botonIntroducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIntroducirActionPerformed
        if (posicionX == NUM_BOTONES_ANCHO - 1 && posicionY < NUM_BOTONES_LARGO - 1) {
            enviar("btnSalto");
            comprobarCasilla();
            posicionY++;
            posicionX = -1;
        }
        if(posicionX == NUM_BOTONES_ANCHO - 1 && posicionY == NUM_BOTONES_LARGO - 1)
            finDelJuego("Has perdido :(");
    }//GEN-LAST:event_botonIntroducirActionPerformed

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
            java.util.logging.Logger.getLogger(MastermindServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MastermindServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MastermindServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MastermindServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MastermindServidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAmarillo;
    private javax.swing.JButton botonAzul;
    private javax.swing.JButton botonIntroducir;
    private javax.swing.JButton botonRetroceder;
    private javax.swing.JButton botonRojo;
    private javax.swing.JButton botonVerde;
    private javax.swing.JLabel combinacion1;
    private javax.swing.JLabel combinacion2;
    private javax.swing.JLabel combinacion3;
    private javax.swing.JLabel combinacion4;
    private javax.swing.JLabel combinacion5;
    private javax.swing.JLabel combinacion6;
    private javax.swing.JLabel combinacion7;
    private javax.swing.JLabel combinacion8;
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
    private javax.swing.JLabel espacio33;
    private javax.swing.JLabel espacio34;
    private javax.swing.JLabel espacio35;
    private javax.swing.JLabel espacio36;
    private javax.swing.JLabel espacio37;
    private javax.swing.JLabel espacio38;
    private javax.swing.JLabel espacio39;
    private javax.swing.JLabel espacio4;
    private javax.swing.JLabel espacio40;
    private javax.swing.JLabel espacio41;
    private javax.swing.JLabel espacio42;
    private javax.swing.JLabel espacio43;
    private javax.swing.JLabel espacio44;
    private javax.swing.JLabel espacio45;
    private javax.swing.JLabel espacio46;
    private javax.swing.JLabel espacio47;
    private javax.swing.JLabel espacio48;
    private javax.swing.JLabel espacio49;
    private javax.swing.JLabel espacio5;
    private javax.swing.JLabel espacio50;
    private javax.swing.JLabel espacio51;
    private javax.swing.JLabel espacio52;
    private javax.swing.JLabel espacio53;
    private javax.swing.JLabel espacio54;
    private javax.swing.JLabel espacio55;
    private javax.swing.JLabel espacio56;
    private javax.swing.JLabel espacio57;
    private javax.swing.JLabel espacio58;
    private javax.swing.JLabel espacio59;
    private javax.swing.JLabel espacio6;
    private javax.swing.JLabel espacio60;
    private javax.swing.JLabel espacio61;
    private javax.swing.JLabel espacio62;
    private javax.swing.JLabel espacio63;
    private javax.swing.JLabel espacio64;
    private javax.swing.JLabel espacio7;
    private javax.swing.JLabel espacio8;
    private javax.swing.JLabel espacio9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel10;
    private javax.swing.JPanel panel11;
    private javax.swing.JPanel panel12;
    private javax.swing.JPanel panel13;
    private javax.swing.JPanel panel14;
    private javax.swing.JPanel panel15;
    private javax.swing.JPanel panel16;
    private javax.swing.JPanel panel17;
    private javax.swing.JPanel panel18;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JPanel panel9;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelMenu1;
    private javax.swing.JPanel panelPistas1;
    private javax.swing.JPanel panelPistas10;
    private javax.swing.JPanel panelPistas11;
    private javax.swing.JPanel panelPistas12;
    private javax.swing.JPanel panelPistas13;
    private javax.swing.JPanel panelPistas14;
    private javax.swing.JPanel panelPistas15;
    private javax.swing.JPanel panelPistas16;
    private javax.swing.JPanel panelPistas2;
    private javax.swing.JPanel panelPistas3;
    private javax.swing.JPanel panelPistas4;
    private javax.swing.JPanel panelPistas5;
    private javax.swing.JPanel panelPistas6;
    private javax.swing.JPanel panelPistas7;
    private javax.swing.JPanel panelPistas8;
    private javax.swing.JPanel panelPistas9;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelPrincipal1;
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
    private javax.swing.JLabel pista33;
    private javax.swing.JLabel pista34;
    private javax.swing.JLabel pista35;
    private javax.swing.JLabel pista36;
    private javax.swing.JLabel pista37;
    private javax.swing.JLabel pista38;
    private javax.swing.JLabel pista39;
    private javax.swing.JLabel pista4;
    private javax.swing.JLabel pista40;
    private javax.swing.JLabel pista41;
    private javax.swing.JLabel pista42;
    private javax.swing.JLabel pista43;
    private javax.swing.JLabel pista44;
    private javax.swing.JLabel pista45;
    private javax.swing.JLabel pista46;
    private javax.swing.JLabel pista47;
    private javax.swing.JLabel pista48;
    private javax.swing.JLabel pista49;
    private javax.swing.JLabel pista5;
    private javax.swing.JLabel pista50;
    private javax.swing.JLabel pista51;
    private javax.swing.JLabel pista52;
    private javax.swing.JLabel pista53;
    private javax.swing.JLabel pista54;
    private javax.swing.JLabel pista55;
    private javax.swing.JLabel pista56;
    private javax.swing.JLabel pista57;
    private javax.swing.JLabel pista58;
    private javax.swing.JLabel pista59;
    private javax.swing.JLabel pista6;
    private javax.swing.JLabel pista60;
    private javax.swing.JLabel pista61;
    private javax.swing.JLabel pista62;
    private javax.swing.JLabel pista63;
    private javax.swing.JLabel pista64;
    private javax.swing.JLabel pista7;
    private javax.swing.JLabel pista8;
    private javax.swing.JLabel pista9;
    // End of variables declaration//GEN-END:variables
}
