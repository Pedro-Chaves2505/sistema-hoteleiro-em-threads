
public class Hospede extends Thread{
  private int id;
  private Quarto quartoOndeHospedado;

  public Hospede(Quarto quarto, int id) {
    this.id = id;
    this.quartoOndeHospedado = quarto;
  }

  public void usarSeuQuarto() {
    this.quartoOndeHospedado.usarOQuarto();
  }

  public void run() {
    this.usarSeuQuarto();
  }  
}