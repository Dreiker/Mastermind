/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread {
    
    ServerSocket socket;
    Socket cliente;
    DataInputStream entrada;
    DataOutputStream salida;
    
    private static final int PUERTO = 9001;
    
    
    public Servidor() throws IOException {
       socket = new ServerSocket(PUERTO); 
       socket.setSoTimeout(10000);
       
    }
    
    @Override
    public void run(){
        while(true){
            try {
                System.out.println("Esperando cliente en puerto " + socket.getLocalPort() + "...");
                cliente = socket.accept();
                System.out.println("Conectado a " + cliente.getRemoteSocketAddress());
                
                entrada = new DataInputStream(cliente.getInputStream());
                System.out.println(entrada.readUTF());
                
                salida = new DataOutputStream(cliente.getOutputStream());
                salida.writeUTF("Mensaje de prueba");
                
                cliente.close();
            } catch(SocketTimeoutException s) {
                System.out.println("Tiempo de espera superado.");
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        public static void main(String[] args) throws Exception {
        new Servidor().start();
        }
}
