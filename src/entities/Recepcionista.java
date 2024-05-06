package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	private FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto;
	private FilaDeRecepcionistas filaDeRecepcionistas;
	private FilaDeQuartosDisponiveis filaDeQuartosDisponiveis;
	private FilaDeQuartosALavar filaDeQuartosALavar;

	private Lock lock = new ReentrantLock();

	public Recepcionista(String nome,FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto, FilaDeQuartosDisponiveis filaDeQuartosDisponiveis, FilaDeQuartosALavar filaDeQuartosALavar, FilaDeRecepcionistas filaDeRecepcionistas) {
		super(nome);
		this.filaDePessoasBuscandoQuarto = filaDePessoasBuscandoQuarto;
		this.filaDeQuartosDisponiveis = filaDeQuartosDisponiveis;
		this.filaDeQuartosALavar = filaDeQuartosALavar;
		this.filaDeRecepcionistas = filaDeRecepcionistas;
	}

	public void run() {
		while (true) {
			while(this.filaDePessoasBuscandoQuarto.size() == 0) {
				try {
					System.out.println("Não há ninguém aguardando na fila");
					System.out.println(this.filaDeQuartosDisponiveis);
					sleep(1000);
				} catch (Exception e) {
					System.out.println("Exceção em recepcionista");
				}			
			}
			Hospede hospedeEmAtendimento = this.filaDePessoasBuscandoQuarto.pop();

			while(this.filaDeQuartosDisponiveis.size() == 0) {
				try {
					sleep(1000);
				} catch (Exception e) {
					System.out.println("Exceção em recepcionista");
				}			
			}
			Quarto quartoDisponivel = this.filaDeQuartosDisponiveis.pop();

			
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
	
	public void retirarPessoaDeQuarto(Hospede hospede){
		Quarto quartoSaindo = hospede.getQuarto();
		quartoSaindo.setHospede(null);
		hospede.setQuarto(null);
		System.out.println(hospede.getName() + " retirado do Quarto " + quartoSaindo.getNumero());
		this.filaDeQuartosDisponiveis.push(quartoSaindo);
		this.filaDeRecepcionistas.push(this);

	}

	// public void atenderCliente() {
	// 	hospedar(quartos.get(0),hospedes.get(0));
	// }
	// public List<Quarto> getQuartos() {
	// 	return quartos;
	// }
}