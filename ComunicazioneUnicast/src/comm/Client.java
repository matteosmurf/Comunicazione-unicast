package comm;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

import javax.imageio.IIOException;
import javax.management.RuntimeErrorException;

public class Client {
    String nome;
    String colore;
    Socket socket;

    public Client(String nome){
        this.nome = nome;
        this.colore = colore;
    }

    public void connetti(String nomeServer, int porta){
        try {
            socket = new Socket(nomeServer, porta);
            System.out.println("1)Connessione avvenuta con successo");
            } catch (ConnectException e) {
                System.err.println("Errore server non in ascolto");
                throw new RuntimeException(e);
            } catch (IOException e) {
                System.err.println("Errore nella fase di connesione");
                throw new RuntimeException(e);
            } 
    }

    public void leggi() {

    }

    public void scrivi() {

    }

    public void chiudi(){
        if(socket != null){
            try {
                socket.close();
                System.out.println("4) chiusura della connessione con il server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
