package comm;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class MainServer {
    public static void main(String[] args) {
       Server s = new Server(60000);
       s.attendi();
       String messaggio = s.leggi();
       System.out.println("Messaggio del client: " + messaggio);
       s.scrivi("ciao client");
       s.chiudi();
       s.termina();
    }
}