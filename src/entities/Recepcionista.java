package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	//public List<Hospede> hospedes ;
	//public List<Quarto> quartos ;
	private Hospede hospede;
	private Quarto quarto;
	private Lock lock = new ReentrantLock();

	public Recepcionista(String nome,Hospede hospede, Quarto quarto) {
		super(nome);
		this.hospede = hospede;
		this.quarto = quarto;
	}

	public void run() {
		System.out.println(this.quarto);
		System.out.println(this.hospede);
		hospedar(this.quarto, this.hospede);
	}
	
	public void hospedar(Quarto quarto,Hospede hospede) {
		lock.tryLock();
		if (quarto.getHospede() == null) {
			quarto.setHospede(hospede);
			//quarto.setNumeroDeHospedes((hospede.getGrupo() < quarto.getNumeroLimiteDePessoas()) ? hospede.getGrupo():hospede.reduzirGrupo(4));
			hospede.setQuarto(quarto);
			System.out.println("Hospede do Quarto " + quarto.getNumero() + " é: " + quarto.getHospede().getName());
			System.out.println("Quarto do " + hospede.getName() + " é: Quarto "+  hospede.getQuarto().getNumero());
			System.out.println("-----" + hospede.getName() + " está no quarto " + quarto.getNumero());
		}
		
		// quarto.getRecepcionista().getQuartos().remove(quarto);
	
		lock.unlock();
	}
	
	// public void atenderCliente() {
	// 	hospedar(quartos.get(0),hospedes.get(0));
	// }
	// public List<Quarto> getQuartos() {
	// 	return quartos;
	// }
}