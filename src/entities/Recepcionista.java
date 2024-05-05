package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Recepcionista extends Thread {
	public List<Hospede> hospedes ;
	private Lock lock = new ReentrantLock();
	private FilaDeQuartosALavar quartosALavar;
	private FilaClientesParaAtender clientesParaAtander;


	public Recepcionista(String nome,List<Hospede> hospedes,FilaClientesParaAtender clientesParaAtander) {
		super(nome);
		this.hospedes = hospedes;
		//this.quartos = quartos;
		this.clientesParaAtander = clientesParaAtander;
	}

	public void run() {
		//atenderCliente(new Quarto(1),new Hospede(getName(), quartosALavar,new Recepcionista(getName(), hospedes, quartos)));
		pegarClienteParaAtender(this);
	}
	
	public void hospedar(Quarto quarto,Cliente clienteAHospedar) { // modificado fila clientes
		lock.tryLock();
		if (quarto.getHospede() == null) {// certo
			quarto.setHospede(clienteAHospedar);// certo
			quarto.setNumeroDeHospedes((clienteAHospedar.getGrupo() < quarto.getNumeroLimiteDePessoas()) ? clienteAHospedar.getGrupo():clienteAHospedar.reduzirGrupo(4));
			//FilaClientesParaAtender filaClientesParaAtender = new FilaClientesParaAtender();
			clienteAHospedar.setQuarto(quarto);
			//quartos.remove(quartos.indexOf(quarto));
			System.out.println(clienteAHospedar.getName() + " está no quarto " + quarto.getNumero());
		}
		
		//quarto.getRecepcionista().getQuartos().remove(quarto);
	
		lock.unlock();
	}
	
	//public void atenderCliente(Quarto quarto,Hospede hospede) { // MODIFICADO PARA O TESTE RECEPCIONISTA - HOSPEDE
		//hospedar(quarto,hospede);
		//hospedar(quartos.get(0),hospedes.get(0));
	//}
	
	// metodo getQuartos aqui

	public String getNomeRecepcionista(){
		return getName();
	}


    public void pegarClienteParaAtender(Recepcionista recepcionista){
		// varios recepcionsitas podem entrar nesse metodo, porém um entrará por vez (ACHO QUE SE ENTRAREM AO MESMO TEMPO, UM RECEPCIIONISTA PODE PEGAR O MESMO CLIENTE QUE OUTRO)
		// em tese, é pra um cliente ser atendido por apenas um recepcionionista
         lock.lock();
		 clientesParaAtander.pop(this);
		 System.out.println("O cliente foi atendido pela recepcionista "+this.getNomeRecepcionista());
        lock.unlock();


	}




}