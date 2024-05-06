package entities;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	static List<Hospede> roomless = new ArrayList<>(); // fila de espera de hospedes
	static List<Quarto> vacant = new ArrayList<>(); // lista de quartos vagos
	static List<Hospede> requests = new ArrayList<>(); // pessoas que querem ser hospedadas
	private Lock lock = new ReentrantLock();

	public Recepcionista(String nome) {
		super(nome);
	}

	public void run() {
		if (lock.tryLock()) {
			try {
				if (requests.size() > 0) {
					if (vacant.size() > 0) {
						Recepcionista.roomless.add(Recepcionista.requests.get(0));
						Recepcionista.requests.remove(0);
					} else {
						Recepcionista.requests.remove(0);
						System.out.println(this.getName()+": não há quartos vagos.");
					}
				}
			} finally {
				lock.unlock();
			}
		}
		if(Recepcionista.roomless.size()>0) {
			checkInClient(Recepcionista.roomless.get(0),0);
		}
	}

	// adiciona o possivel hospede a lista de pessoas que querem ser hospedadas
	// esse método estático será acionado pelo freguês, ele tenta entrar para a lista, e enquanto estiver na lista ele espera, caso ele seja removido e não esteja sendo atendido, ele para de esperar e faz outra coisa, eventualmente retornando
	// possível mecânica: esperar por um determinado período de tempo
	public static boolean lodgeRequest(Hospede hospede) {
		Recepcionista.requests.add(hospede);

		while(!Recepcionista.roomless.contains(hospede)) {
			if(Recepcionista.requests.contains(hospede)) {
				if(!Recepcionista.roomless.contains(hospede)) {
					return false;
				} else {
					return true;
				}
			}
		}
		return true;
	}

	public void checkInClient(Hospede hospede, Integer recursion) {
		Quarto quarto = Recepcionista.vacant.get(0);
		if (lock.tryLock()) {
			try {
				if ((null==quarto.getHospede()) && (null==hospede.getQuarto())) {
					quarto.setHospede(hospede);
					Integer novo_grupo = hospede.reduzirGrupo(quarto.getNumeroLimiteDePessoas());
					// novo_grupo recebe quem n pode entrar no limite, para ser passado para outro hospede
					quarto.setNumeroDeHospedes(hospede.getGrupo());
					hospede.setQuarto(quarto);
					Recepcionista.vacant.remove(Recepcionista.vacant.indexOf(quarto));
					if (Recepcionista.roomless.contains(hospede)) {
						// para apagar o hospede da lista de espera, caso ele esteja nela
						// casos em que pode não estar é justamente quando ele vem do atendimento de outro hospede
						Recepcionista.roomless.remove(Recepcionista.roomless.indexOf(hospede));
					}
					System.out.println(this.getName() + ": " + hospede.getName() + " está no quarto " + quarto.getNumero());
					if (novo_grupo > 0) {
						// caso tenha mais gente no grupo, um novo hospede será criado e será atendido imediatamente
						this.checkInClient(new Hospede(hospede.getNome()+" "+recursion,novo_grupo,hospede.getQuartosALavar()),recursion+1);
					}
				} else if (quarto.getHospede() != null) {
					// caso o hospede ja tenha quarto, ele será removido da lista de espera
					Recepcionista.roomless.remove(Recepcionista.roomless.indexOf(hospede));
				} else {
					// caso o quarto ja tenha dono, o quarto será removido dos vagos
					Recepcionista.vacant.remove(Recepcionista.vacant.indexOf(quarto));
				}
			} finally {
				lock.unlock();
			}
		}
	}
}