package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private BufferedReader in;
    private BufferedWriter out;


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
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println("2) Connessione con il client avvenuta e data socket creato: " + clientSocket.getInetAddress());
        } catch (IOException e) {
            System.err.println("Problemi di connessione con il client");
            throw new RuntimeException(e);
        }
        return clientSocket;
    }

    public String leggi(){
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Errore durante la lettura");
            return null;
        }
    }

    public void scrivi(String messaggio) {
        try {
            out.write(messaggio);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura");
        }
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
        try {
            serverSocket.close();
            System.out.println("Server terminato");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}

