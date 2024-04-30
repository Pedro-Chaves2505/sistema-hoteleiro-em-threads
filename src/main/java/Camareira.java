public class Camareira extends Thread{
  private int id;
  private Quarto quartoALavar;

  public Camareira(Quarto quarto, int id) {
    this.id = id;
    this.quartoALavar = quarto;
  }

  public void arrumarQuarto() {
    this.quartoALavar.usarOQuarto();
  }

  public void run() {
    this.arrumarQuarto();
  }  
}