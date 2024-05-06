package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread{
	private String nome;
	private FilaDeQuartosDisponiveis filaDeQuartosDisponiveis;
	private FilaDeQuartosALavar quartosALavar;
	private Lock lock = new ReentrantLock();

	public Camareira(String nome, FilaDeQuartosALavar quartosALavar,FilaDeQuartosDisponiveis filaDeQuartosDisponiveis) {
		super(nome);
		this.quartosALavar = quartosALavar;
		this.filaDeQuartosDisponiveis = filaDeQuartosDisponiveis;
	}
	public void setQuartosALavar(FilaDeQuartosALavar quartosALavar) {
		this.quartosALavar = quartosALavar;
	}

	public void arrumarQuarto() {
		try {
			Quarto quartoALavar = this.quartosALavar.pop();
			quartoALavar.entrarNoQuarto(getName());
			// if(quartoALavar.getHospede() == null){
			// 	filaDeQuartosDisponiveis.push(quartoALavar);
			// }
		} catch (Exception e) {
			System.out.println("Oops, alguma camareira pegou a chave quando a " + getName() +
			 " ia pegar.Mas, sem problemas, ela vai esperar haver um cliente colocar uma outra chave!");
		}
	}

	public void run() {
		while(true){
			while(this.quartosALavar.size() == 0) {
				try {
					System.out.println(getName() + " esperando a chave estar na recepção...");
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
		
	}
	public String getNome() {
		return nome;
	}
}