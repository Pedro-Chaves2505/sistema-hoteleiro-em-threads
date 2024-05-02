package entities;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	public ArrayList<Hospede> hospedes ;
	public ArrayList<Quarto> quartos ;
	private Lock lock = new ReentrantLock();

	public Recepcionista(String nome,ArrayList<Hospede> hospedes, ArrayList<Quarto> quartos) {
		super(nome);
		this.hospedes = hospedes;
		this.quartos = quartos;
	}

	public void run() {
		atenderCliente();
	}
	
	public void hospedar(Quarto quarto,Hospede hospede) {
		lock.tryLock();
		if (hospede == null) {
			quarto.setHospede(hospede);
			quarto.setNumeroDeHospedes((hospede.getGrupo() < quarto.getNumeroLimiteDePessoas()) ? hospede.getGrupo():hospede.reduzirGrupo(4));
			hospede.setQuarto(quarto);
			System.out.println(hospede.getName() + " está no quarto " + quarto.getNumero());
		}
		
		quarto.getRecepcionista().getQuartos().remove(quarto);
	
		lock.unlock();
	}
	
	public void atenderCliente() {
		hospedar(quartos.get(0),hospedes.get(0));
	}
	public ArrayList<Quarto> getQuartos() {
		return quartos;
	}
}