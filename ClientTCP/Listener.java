/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttcp;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arcadal
 */
class Listener implements Runnable {
  private Socket client;

    Listener(Socket client) {
        this.client = client;
        System.out.println("In ascolto con: " + client);
    }

    public void run(){
        
        BufferedReader in = null;
        try{
          in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
          System.out.println("IO Error!!!");
          System.exit(-1);
        }

        String testoDaServer = "";
        try {
            while ((testoDaServer = in.readLine()) != null) {
                System.out.println(testoDaServer);
                if (testoDaServer.contains("Bye.")) {
                    client.close();
                    System.exit(0);
                    break;
                }
            }
        } catch (IOException e) { 
            try {
                System.out.println("Connessione terminata dal Server");
                client.close();
                System.exit(-1);
            } catch (IOException ex) {
                System.out.println("Error nella chiusura del Socket");
                System.exit(-1);
            }
        }
    }
}