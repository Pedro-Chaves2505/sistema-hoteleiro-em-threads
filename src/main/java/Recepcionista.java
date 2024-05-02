import java.util.ArrayList;

public class Recepcionista extends Thread {
  static ArrayList<Hospede> unattended = new ArrayList<Hospede>();
  static ArrayList<Quarto> vacant = new ArrayList<Quarto>();

  public Recepcionista(String nome) {
    super(nome);
  }

  public void run() {
    atenderCliente();
  }

  public void atenderCliente() {
    vacant.get(vacant.size()-1).Hospedar((unattended.get(unattended.size()-1)))
  }
}