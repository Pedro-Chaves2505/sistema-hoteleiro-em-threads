import java.util.concurrent.locks.ReentrantLock;

public class Quarto {
  private Boolean temAlguemDentroDele = false;
  private ReentrantLock lock = new ReentrantLock();

  public String usarOQuarto() {
    lock.lock();
    if (temAlguemDentroDele) {
      System.out.println("!!!!!!!Uso simultaneo!");
      return "===uso simultaneo===";
    }
    this.temAlguemDentroDele = true;
    System.out.println("Estou dentro do quarto usando ele!");
    System.out.println("Estou saindo do quarto!");
    this.temAlguemDentroDele = false;
    lock.unlock();

    return "uso normal";
  }
  // public void setHospede(Hospede hospede){
  // this.hospede = hospede;
  // }
}