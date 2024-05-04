package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	public List<Hospede> hospedes ; // lista de hospedes
	public List<Quarto> quartos ; // lista de quartos
	private Lock lock = new ReentrantLock();
	private boolean chave = false;

	public Recepcionista(String nome,List<Hospede> hospedes, List<Quarto> quartos) {
		super(nome); // cada thread recepcionista terá o nome que dado na hora da inicialização 
		this.hospedes = hospedes;
		this.quartos = quartos;
	}

	public void run() {
		atenderCliente(); // ao inicializar a thread apenas o metodo atenderCliente é chamado
	}
	
	public void hospedar(Quarto quarto,Hospede hospede) {
		lock.tryLock();
		if (quarto.getHospede() == null) { // REGRA:  devem alocar hóspedes apenas em quartos vagos; 
			quarto.setHospede(hospede); 
			quarto.setNumeroDeHospedes((hospede.getGrupo() < quarto.getNumeroLimiteDePessoas()) ? hospede.getGrupo():hospede.reduzirGrupo(4)); // REGRA: MÁZIMO DE 4 HOSPEDES POR QUARTO
			hospede.setQuarto(quarto);
			quartos.remove(quartos.indexOf(quarto)); //  Remove o quarto da lista de quartos disponíveis.
			System.out.println(hospede.getName() + " esta no quarto " + quarto.getNumero());
			System.out.println("O cliente "+hospede.getName() + " esta hospedado no quarto "+quarto.getNumero()); // Adicionando essa linha para imprimir a saída desejada.
		}
		
		quarto.getRecepcionista().getQuartos().remove(quarto); // Remove o quarto da lista de quartos da recepcionista.
	
		lock.unlock();
	}
	
	public void atenderCliente() {  
		lock.lock();
		try{
		for (int i = 0; i < hospedes.size();i++){
			System.out.println("--------SIZE DA LISTA QUARTO--"+quartos.size());
			System.out.println("--------SIZE DA LISTA HOSPEDES--"+hospedes.size());
		hospedar(quartos.get(i),hospedes.get(i));
		}
		}finally{
			lock.unlock();
		}
	}
	public List<Quarto> getQuartos() {
		return quartos;
	}

	//public void receberChave(Quarto quarto) {
		// Lógica para receber a chave do quarto
		//System.out.println("Recebendo a chave do quarto " + quarto.getNumero() + " na recepção.");
		//quarto.setRecepcionista(this);


	}
	

	