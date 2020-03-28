/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttcp;

/**
 *
 * @author Arcadal
 */
import java.net.*;
import java.io.*;

public class ClientTCP {
    
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
		
        String Username;
	String hostName = "127.0.0.1";
	int portNumber = 1234;
        
	try {
            InetAddress address = InetAddress.getByName(hostName);
			
            Socket clientSocket = new Socket(address, portNumber);
		
            System.out.println("Scrivi qui e premi ENTER per inviare.\n");
			
            Listener l;
            try {
                l = new Listener(clientSocket);
                Thread t = new Thread(l);
                t.start();
            } catch (Exception e) { System.out.println("Connessione non riuscita"); }
		
            PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);
			
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            
            System.out.print(">");
            while ((userInput = stdIn.readLine()) != null) {
		out.println(userInput);
                System.out.println("Messaggio spedito al server: " + userInput);
                System.out.print(">");
            }
            clientSocket.close();
            System.out.println("Connessione terminata!");
	}
        catch (IOException e) { System.out.println("Connessione terminata dal server: "); e.printStackTrace(); }
    }
    
}