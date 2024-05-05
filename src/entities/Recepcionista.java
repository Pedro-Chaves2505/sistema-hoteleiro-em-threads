package entities;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	static List<Hospede> roomless = ArrayList<>();
	static List<Quarto> vacant = ArrayList<>();
	private Lock lock = new ReentrantLock();

	public Recepcionista(String nome,List<Hospede> hospedes, List<Quarto> quartos) {
		super(nome);
		this.hospedes = hospedes;
		this.quartos = quartos;
	}

	public void run() {
		atenderCliente();
	}
	
	public void atenderCliente() {
		Quarto quarto = Recepcionista.vacant.get(0);
		Hospede hospede = Recepcionista.roomless.get(0);
		if (lock.tryLock()) {
			try {
				if (quarto.getHospede() == null and hospede.getQuarto() == null) {
					quarto.setHospede(hospede);
					hospede.reduzirGrupo(quarto.getNumeroLimiteDePessoas());
					quarto.setNumeroDeHospedes(hospede.getGrupo());
					hospede.setQuarto(quarto);
					Recepcionista.vacant.remove(Recepcionista.vancant.indexOf(quarto));
					Recepcionista.roomless.remove(Recepcionista.roomless.indexOf(hospede));
					System.out.println(hospede.getName() + " está no quarto " + quarto.getNumero());
				} else if (quarto.getHospede() != null) {
					Recepcionista.roomless.remove(Recepcionista.roomless.indexOf(hospede));
				} else {
					Recepcionista.vacant.remove(Recepcionista.vacant.indexOf(quarto));
				}
			}
		}
	}

	public List<Quarto> getQuartos() {
		return quartos;
	}
}