package comm;

public class MainClient {
    public static void main(String[] args) {
        Client c = new Client("matteo", "verde");
        c.connetti("localhost", 60000);
        c.scrivi();
        String risposta = c.leggi();
        System.out.println("Risposta del server: " + risposta);
        c.chiudi();
    }
}