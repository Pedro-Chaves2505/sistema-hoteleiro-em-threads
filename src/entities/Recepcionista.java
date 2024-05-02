package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	public List<Hospede> hospedes ;
	public List<Quarto> quartos ;
	private Lock lock = new ReentrantLock();

	public Recepcionista(String nome,List<Hospede> hospedes, List<Quarto> quartos) {
		super(nome);
		this.hospedes = hospedes;
		this.quartos = quartos;
	}

	public void run() {
		atenderCliente();
	}
	
	public void hospedar(Quarto quarto,Hospede hospede) {
		lock.tryLock();
		if (quarto.getHospede() == null) {
			quarto.setHospede(hospede);
			quarto.setNumeroDeHospedes((hospede.getGrupo() < quarto.getNumeroLimiteDePessoas()) ? hospede.getGrupo():hospede.reduzirGrupo(4));
			hospede.setQuarto(quarto);
			quartos.remove(quartos.indexOf(quarto));
			System.out.println(hospede.getName() + " está no quarto " + quarto.getNumero());
		}
		
		quarto.getRecepcionista().getQuartos().remove(quarto);
	
		lock.unlock();
	}
	
	public void atenderCliente() {
		hospedar(quartos.get(0),hospedes.get(0));
		
	}
	public List<Quarto> getQuartos() {
		return quartos;
	}
}