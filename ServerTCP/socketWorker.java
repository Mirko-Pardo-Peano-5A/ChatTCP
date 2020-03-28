
package servertcp;

import java.net.*;
import java.io.*;

/**
 *
 * @author Arcadal
 */
class socketWorker implements Runnable, InviaMessaggio, RiceviMessaggio  {
    private  static final  MessageManager gestoreMessaggi = new MessageManager();
    private Socket client;
    private PrintWriter out = null;

    //Constructor: inizializza le variabili
    socketWorker(Socket client) {
        this.client = client;
        gestoreMessaggi.addClient(this);
        System.out.println("Connesso con: " + client);
    }
    
    public void messaggioReceived(String m) {
        this.gestoreMessaggi.sendNewMessaggio(m);
    }
    
    public void sendMessaggio(String messaggio) {
        
        out.println("Server->> " + messaggio);
        
    }

    public void run(){
        
        BufferedReader in = null;
        try{
          in = new BufferedReader(new InputStreamReader(client.getInputStream()));
          out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
          System.out.println("Errore: in|out fallito");
          System.exit(-1);
        }

        String line = "";
        int clientPort = client.getPort();
        while(line != null){
          try{
            line = in.readLine();
            messaggioReceived(line);
            System.out.println(clientPort + ">> " + line);
           } catch (IOException e) {
            System.out.println("lettura da socket fallito");
            System.exit(-1);
           }
        }
        try {
            client.close();
            System.out.println("Connessione con client: " + client + " terminata!");
        } catch (IOException e) {
            System.out.println("Errore connessione con client: " + client);
        }
    }

    @Override
    public void InviaMessaggio(String m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MessaggioRicevuto(String m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}