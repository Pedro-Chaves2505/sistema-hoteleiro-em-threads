package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread{
	private String nome;
	private List<Quarto> quartosDisponiveis;
	private List<Quarto> quartoALavar;
	private Lock lock = new ReentrantLock();

	public Camareira(String nome,List<Quarto> quartos,List<Quarto> quartosDisponiveis) {
		super(nome);
		quartoALavar = quartos;
		this.quartosDisponiveis = quartosDisponiveis;
	}
	public void setQuartoALavar(List<Quarto> quartoALavar) {
		this.quartoALavar = quartoALavar;
	}

	public void arrumarQuarto() {
		this.quartoALavar.get(0).usarOQuarto(getName());
	}

	public void run() {
		while(quartoALavar.size() == 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lock.tryLock();
		this.arrumarQuarto();
		if(quartoALavar.get(0).getSendoUsado()){
			this.quartoALavar.remove(0);
		}else {
			this.quartosDisponiveis.add(quartoALavar.get(0));
			this.quartoALavar.remove(0);
		}
		System.out.println("Quarto lavado");
		lock.unlock();
	}
	public String getNome() {
		return nome;
	}
}
