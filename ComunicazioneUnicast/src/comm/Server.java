package comm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    Socket clientSocket;
    int porta;

    public Server(int porta){
        this.porta = porta;
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("1) Server in ascolto sulla porta" + porta);
        } catch (IOException e) {
            System.err.println("Errore del server nella fase di binding");
            throw new RuntimeException(e);
        }
    }

    public Socket attendi(){

        try {
            clientSocket = serverSocket.accept();
            System.out.println("2) Connessione con il client avvenuta e data socket creato");
        } catch (IOException e) {
            System.err.println("Problemi di connessione con il client");
            throw new RuntimeException(e);
        }
        return clientSocket;
    }

    public void leggi(){

    }

    public void scrivi() {

    }

    public void chiudi(){
        try {
            clientSocket.close();
            System.out.println("5) chiusura comunicazione");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void termina(){

    }
}

