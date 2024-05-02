import java.util.concurrent.locks.ReentrantLock;

public class Quarto {
  private Boolean temAlguemDentroDele = false;
  private Hospede hospede;
  private ReentrantLock lock = new ReentrantLock();
  private int numero;

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

  public void hospedar(Hospede hospede) {
    lock.tryLock();
    if (this.hospede == null) {
      this.hospede = hospede;
      this.hospede.setQuarto(this);
      System.out.println(this.hospede + "está no quarto " + this.numero);
    }
    Recepcionista.vacant.remove(this);
    lock.unlock();
  }

  public void setHospede(Hospede hospede) {
    this.hospede = hospede;
  }

  public Hospede getHospede() {
    return this.hospede;
  }

  public Quarto(int numero) {
    this.numero = numero;
  }
}