/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.util.ArrayList;

/**
 *
 * @author Arcadal
 */
class MessageManager {

    private String messaggio;
    private ArrayList<SocketWorker> workers = new ArrayList<>();

    void addClient(SocketWorker worker) {
        this.workers.add(worker);
    }

    void removeClient(SocketWorker worker) {
        this.workers.remove(worker);
    }

    synchronized void sendNewMessaggio(String m) {
        this.messaggio = m;
        for (SocketWorker worker : this.workers) {
            worker.sendMessaggio(this.messaggio);
        }
    }

    interface InviaMessaggio {

        public void sendMessaggio(String m);
    }

    interface RiceviMessaggio {

        public void messaggioReceived(String m);

    }

}
