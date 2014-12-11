/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.awt.Color;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mastermind.MastermindCliente;

public class Cliente extends Thread {

    MastermindCliente juego;
    int posicionX = 0;
    int posicionY = 0;
    int posicionPista = 0;

    Socket socketServidor;
    DataOutputStream salida;
    OutputStream salidaDelServidor;
    DataInputStream entrada;
    InputStream entradaAlServidor;

    private final String DIRECCION_SERVIDOR = "localhost";
    private final int PUERTO = 9001;

    public Cliente(MastermindCliente juego) {
        this.juego = juego;
        try {
            System.out.println("Intentando conectar a " + DIRECCION_SERVIDOR + ":" + PUERTO);
            socketServidor = new Socket(DIRECCION_SERVIDOR, PUERTO);
            System.out.println("Conexión establecida.");

            salida = new DataOutputStream(socketServidor.getOutputStream());
            salida.writeUTF("Primer mensaje desde el cliente.");

            entrada = new DataInputStream(socketServidor.getInputStream());
            System.out.println("Servidor: " + entrada.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                entrada = new DataInputStream(socketServidor.getInputStream());
                String datoEntrada = entrada.readUTF();

                //Control del juego
                if (datoEntrada.equals("btnRojo")) {
                    incrementarCasilla(Color.red);
                } else if (datoEntrada.equals("btnVerde")) {
                    incrementarCasilla(Color.green);
                } else if (datoEntrada.equals("btnAzul")) {
                    incrementarCasilla(Color.blue);
                } else if (datoEntrada.equals("btnAmarillo")) {
                    incrementarCasilla(Color.yellow);
                } else if (datoEntrada.equals("btnSalto")) {
                    posicionY++;
                    posicionX = 0;
                } else if (datoEntrada.equals("btnRetroceder")) {
                    retroceder();
                } else if (datoEntrada.equals("finVictoria")) {
                    fin("¡Has ganado!");
                } else if (datoEntrada.equals("finDerrota")) {
                    fin("Has perdido :(");
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fin(String string) {
        juego.getBotonRojo().setEnabled(false);
        juego.getBotonVerde().setEnabled(false);
        juego.getBotonAzul().setEnabled(false);
        juego.getBotonAmarillo().setEnabled(false);
        juego.getBotonIntroducir().setEnabled(false);
        juego.getBotonRetroceder().setEnabled(false);
        JOptionPane.showMessageDialog(juego, string);
    }

    private void incrementarCasilla(Color color) {
        juego.getBotonesJugador2()[posicionY][posicionX].setBackground(color);
        if (posicionX < 3) {
            posicionX++;
        }
    }

    private void retroceder() {
        juego.getBotonesJugador2()[posicionY][posicionX].setBackground(new Color(238, 248, 251));
        if (posicionX > 0) {
            posicionX--;
        }
    }

    public void enviar(String string) throws IOException {
        salida = new DataOutputStream(socketServidor.getOutputStream());
        salida.writeUTF(string);
    }
}
