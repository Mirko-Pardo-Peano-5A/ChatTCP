/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

/**
 *
 * @author Arcadal
 */
import java.net.*;
import java.io.*;

public class ServerTCP {

    public static void main(String[] args) {

        int portNumber = 1234;

        try {
            ServerSocket server = new ServerSocket(portNumber);
            System.out.println("Server chatTCP in esecuzione...");

            while (true) {
                socketWorker w;
                try {
                    Socket newSocket = server.accept();
                    w = new socketWorker(newSocket);
                    Thread t = new Thread(w);
                    t.start();
                } catch (IOException e) {
                    System.out.println("Connessione non riuscita ");
                    System.exit(-1);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore! Porta: " + portNumber + " non disponibile");
            System.exit(-1);
        }

    }
}
