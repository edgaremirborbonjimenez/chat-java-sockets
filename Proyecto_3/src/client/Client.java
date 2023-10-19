/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemb
 */
public class Client implements Runnable {

    private int puerto;
    private String mensaje;
    private String ip;

    public Client(String ip, int puerto, String mensaje) {
        this.ip = ip;
        this.puerto = puerto;
        this.mensaje = mensaje;

    }

    @Override
    public void run() {
        DataInputStream in;
        DataOutputStream out;

        try {
            Socket socket = new Socket(ip, this.puerto);

            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(this.mensaje);

            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
