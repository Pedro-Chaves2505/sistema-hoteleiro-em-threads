package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread{
	private String nome;
	private List<Quarto> quartosDisponiveis;
	private List<Quarto> quartosALavar;
	private Lock lock = new ReentrantLock();

	public Camareira(String nome,List<Quarto> quartos,List<Quarto> quartosDisponiveis) {
		super(nome);
		quartosALavar = quartos;
		this.quartosDisponiveis = quartosDisponiveis;
	}
	public void setQuartosALavar(List<Quarto> quartosALavar) {
		this.quartosALavar = quartosALavar;
	}

	public void arrumarQuarto() {
		this.quartosALavar.get(0).usarOQuarto(getName());
	}

	public void run() {
		while(quartosALavar.size() == 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lock.tryLock();
		this.arrumarQuarto();
		if(quartosALavar.get(0).getSendoUsado()){
			this.quartosALavar.remove(0);
		}else {
			this.quartosDisponiveis.add(quartosALavar.get(0));
			this.quartosALavar.remove(0);
		}
		System.out.println("Quarto lavado");
		lock.unlock();
	}
	public String getNome() {
		return nome;
	}
}