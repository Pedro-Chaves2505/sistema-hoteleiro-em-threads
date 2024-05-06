package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	private FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto;
	private FilaDeQuartosDisponiveis filaDeQuartosDisponiveis;
	private Lock lock = new ReentrantLock();

	public Recepcionista(String nome,FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto, FilaDeQuartosDisponiveis filaDeQuartosDisponiveis) {
		super(nome);
		this.filaDePessoasBuscandoQuarto = filaDePessoasBuscandoQuarto;
		this.filaDeQuartosDisponiveis = filaDeQuartosDisponiveis;
	}

	public void run() {
		while (true) {
			while(this.filaDeQuartosDisponiveis.size() == 0) {
				try {
					sleep(1000);
				} catch (Exception e) {
					System.out.println("Exceção em recepcionista");
				}			
			}
			Quarto quartoDisponivel = this.filaDeQuartosDisponiveis.pop();

			while(this.filaDePessoasBuscandoQuarto.size() == 0) {
				try {
					sleep(1000);
				} catch (Exception e) {
					System.out.println("Exceção em recepcionista");
				}			
			}
			Hospede hospedeEmAtendimento = this.filaDePessoasBuscandoQuarto.pop();
			hospedar(quartoDisponivel, hospedeEmAtendimento);
		}
	}
	
	public void hospedar(Quarto quarto,Hospede hospede) {
		
		if (quarto.getHospede() == null) {
			quarto.setHospede(hospede);
			//quarto.setNumeroDeHospedes((hospede.getGrupo() < quarto.getNumeroLimiteDePessoas()) ? hospede.getGrupo():hospede.reduzirGrupo(4));
			hospede.setQuarto(quarto);
			System.out.println("Hospede do Quarto " + quarto.getNumero() + " é: " + quarto.getHospede().getName());
			System.out.println("Quarto do " + hospede.getName() + " é: Quarto "+  hospede.getQuarto().getNumero());
			System.out.println("-----" + hospede.getName() + " está no quarto " + quarto.getNumero());
		}
		
		// quarto.getRecepcionista().getQuartos().remove(quarto);
	
		System.out.println(this.lock);

	}
	
	// public void atenderCliente() {
	// 	hospedar(quartos.get(0),hospedes.get(0));
	// }
	// public List<Quarto> getQuartos() {
	// 	return quartos;
	// }
}