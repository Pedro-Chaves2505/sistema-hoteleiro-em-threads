package entities;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	static List<Hospede> roomless = ArrayList<>(); // fila de espera de hospedes
	static List<Quarto> vacant = ArrayList<>(); // lista de quartos vagos
	private Lock lock = new ReentrantLock();

	public Recepcionista(String nome) {
		super(nome);
	}

	public void run() {
		atenderCliente(Recepcionista.roomless.get(0),0);
	}
	
	public void atenderCliente(Hospede hospede, Integer recursion) {
		Quarto quarto = Recepcionista.vacant.get(0);
		if (lock.tryLock()) {
			try {
				if (quarto.getHospede() == null and hospede.getQuarto() == null) {
					quarto.setHospede(hospede);
					Integer novo_grupo = hospede.reduzirGrupo(quarto.getNumeroLimiteDePessoas();
					// novo_grupo recebe quem n pode entrar no limite, para ser passado para outro hospede
					quarto.setNumeroDeHospedes(hospede.getGrupo());
					hospede.setQuarto(quarto);
					Recepcionista.vacant.remove(Recepcionista.vancant.indexOf(quarto));
					if (Recepcionista.roomless.contains(hospede)) {
						// para apagar o hospede da lista de espera, caso ele esteja nela
						// casos em que pode não estar é justamente quando ele vem do atendimento de outro hospede
						Recepcionista.roomless.remove(Recepcionista.roomless.indexOf(hospede));
					}
					System.out.println(this.nome + ": " + hospede.getName() + " está no quarto " + quarto.getNumero());
					if (novo_grupo > 0) {
						// caso tenha mais gente no grupo, um novo hospede será criado e será atendido imediatamente
						this.atenderCliente(new Hospede(hospede.getNome()+" "+recursion,novo_grupo,hospede.getQuartosALavar()),recursion+1);
					}
				} else if (quarto.getHospede() != null) {
					// caso o hospede ja tenha quarto, ele será removido da lista de espera
					Recepcionista.roomless.remove(Recepcionista.roomless.indexOf(hospede));
				} else {
					// caso o quarto ja tenha dono, o quarto será removido dos vagos
					Recepcionista.vacant.remove(Recepcionista.vacant.indexOf(quarto));
				}
			}
		}
	}
}