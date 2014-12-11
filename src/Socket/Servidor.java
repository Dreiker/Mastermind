/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mastermind.MastermindServidor;

public class Servidor extends Thread {
  
    MastermindServidor juego;
    int posicionX = 0;
    int posicionY = 0;
    int posicionPista = 0;

    ServerSocket socket;
    Socket socketCliente;
    DataInputStream entrada;
    DataOutputStream salida;

    private static final int PUERTO = 9001;

    public Servidor(MastermindServidor juego) throws IOException {

        this.juego = juego;
        socket = new ServerSocket(PUERTO);
        System.out.println("Esperando cliente en puerto " + socket.getLocalPort() + "...");
        socketCliente = socket.accept();
        System.out.println("Conectado a " + socketCliente.getRemoteSocketAddress());

        salida = new DataOutputStream(socketCliente.getOutputStream());
        salida.writeUTF("Mensaje de prueba");
        entrada = new DataInputStream(socketCliente.getInputStream());

    }

    @Override
    public void run() {
        try {
            while (true) {
                String datoEntrada = entrada.readUTF();
                System.out.println("Cliente: " + datoEntrada);

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
        } catch (SocketTimeoutException s) {
            System.out.println("Tiempo de espera superado.");
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
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
        salida.writeUTF(string);
    }

    public String recibido() throws IOException {
        return entrada.readUTF();
    }

//        public static void main(String[] args) throws Exception {
//        new Servidor().start();
//        }
}
