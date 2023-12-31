/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemb
 */
public class Server extends Observable implements Runnable {

    private int puerto;

    public Server(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket server = null;
        Socket socket = null;
        DataInputStream in;

        try {
            server = new ServerSocket(this.puerto);
            System.out.println("Servidor iniciado" + puerto);

            //Siempre estará escuchando peticiones
            while (true) {

                socket = server.accept();
                System.out.println("Cliente conectado");

                in = new DataInputStream(socket.getInputStream());

                //Leo el mensaje que me envian
                String mensaje = in.readUTF();

                System.out.println("Cliente:" + mensaje);

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                socket.close();
                System.out.println("Cliente desconectado");

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
