package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

import javax.imageio.IIOException;
import javax.management.RuntimeErrorException;

public class Client {

    private BufferedReader in;
    private BufferedWriter out;

    String nome;
    String colore;
    Socket socket;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_VERDE = "\u001B[32m";

    public Client(String nome, String colore){
        this.nome = nome;
        this.colore = colore;
    }

    public void connetti(String nomeServer, int porta){
        try {
            socket = new Socket(nomeServer, porta);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("1)Connessione avvenuta con successo");
            } catch (ConnectException e) {
                System.err.println("Errore server non in ascolto");
                throw new RuntimeException(e);
            } catch (IOException e) {
                System.err.println("Errore nella fase di connesione");
                throw new RuntimeException(e);
            } 
    }

    public String leggi() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println("Errore lettura");
            return null;
        }
    }

    public void scrivi() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserisci un messaggio da inviare al server: ");
            String messaggio = input.readLine();
            

            String messaggioColorato = coloraMessaggio(messaggio);
            out.write(messaggioColorato);
            out.newLine(); //terminatore riga
            out.flush();
        } catch (IOException e) {
            System.err.println("Errore scrittura messaggio");
        }
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


    private String coloraMessaggio(String messaggio){
        if(colore == "verde"){
            return ANSI_VERDE + messaggio + ANSI_RESET;
        } else{
            return messaggio;
        }
    }
}
