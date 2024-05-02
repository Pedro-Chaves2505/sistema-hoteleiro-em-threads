import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class FilaDeQuartosALavar {
  private ArrayList<Quarto> listaDeQuartosALavar = new ArrayList<>();
  private ReentrantLock lock = new ReentrantLock();

  public FilaDeQuartosALavar(ArrayList<Quarto> listaDeQuartosALavar) {
    this.listaDeQuartosALavar = listaDeQuartosALavar;
  }

  public Quarto pop() {
    this.lock.lock();
    Quarto quartoALavar = this.listaDeQuartosALavar.remove(0);
    this.lock.unlock();
    return quartoALavar;

  }
}