/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    
    Socket client;
    DataOutputStream salida;
    OutputStream salidaDelServidor;
    DataInputStream entrada;
    InputStream entradaAlServidor;
    
    private final String DIRECCION_SERVIDOR = "localhost";
    private final int PUERTO = 9001;
    
     public Cliente() {

        try {
            System.out.println("Intentando conectar a " + DIRECCION_SERVIDOR + " en puerto " + PUERTO);
            client = new Socket(DIRECCION_SERVIDOR, PUERTO);
            System.out.println("Conexión establecida con " + client.getRemoteSocketAddress());
            
            salidaDelServidor = client.getOutputStream();
            salida = new DataOutputStream(salidaDelServidor);
            salida.writeUTF("Mensaje desde: " + client.getLocalSocketAddress());
            
            entradaAlServidor = client.getInputStream();
            entrada = new DataInputStream(entradaAlServidor);
            System.out.println("Mensaje del servidor: " + entrada.readUTF());
            
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   public static void main(String [] args) {
       new Cliente();
   }
}
