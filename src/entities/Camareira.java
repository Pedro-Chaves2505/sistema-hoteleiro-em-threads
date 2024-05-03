package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread{
	private String nome;
	private List<Quarto> quartosDisponiveis;
	private FilaDeQuartosALavar quartosALavar;
	private Lock lock = new ReentrantLock();

	public Camareira(String nome, FilaDeQuartosALavar quartosALavar,List<Quarto> quartosDisponiveis) {
		super(nome);
		this.quartosALavar = quartosALavar;
		this.quartosDisponiveis = quartosDisponiveis;
	}
	public void setQuartosALavar(FilaDeQuartosALavar quartosALavar) {
		this.quartosALavar = quartosALavar;
	}

	public void arrumarQuarto() {
        Quarto quartoQueVaiLavarAgora = this.quartosALavar.peek();
        quartoQueVaiLavarAgora.entrarNoQuarto(getName());
		this.quartosALavar.pop();
	}

	public void run() {
		while(this.quartosALavar.size() == 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lock.tryLock();
		this.arrumarQuarto();
		lock.unlock();
	}
	public String getNome() {
		return nome;
	}
}