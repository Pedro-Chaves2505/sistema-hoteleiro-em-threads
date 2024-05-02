import java.util.ArrayList;

public class Camareira extends Thread{
  private int id;
  private FilaDeQuartosALavar filaDeQuartosALavar;

  public Camareira(FilaDeQuartosALavar filaDeQuartosALavar, int id) {
    System.out.println("123");

    this.id = id;
    this.filaDeQuartosALavar = filaDeQuartosALavar;
  }

  public void arrumarQuarto() {
    System.out.println("452");

    this.filaDeQuartosALavar.pop().usarOQuarto();
  }

  public void run() {
    System.out.println("2620");

    this.arrumarQuarto();
  }  
}