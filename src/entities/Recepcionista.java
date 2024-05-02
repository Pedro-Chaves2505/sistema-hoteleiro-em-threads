package entities;

import java.util.ArrayList;

public class Recepcionista extends Thread {
	public ArrayList<Hospede> hospedes ;
	public ArrayList<Quarto> quartos ;

	public Recepcionista(String nome,ArrayList<Hospede> hospedes, ArrayList<Quarto> quartos) {
		super(nome);
		this.hospedes = hospedes;
		this.quartos = quartos;
	}

	public void run() {
		atenderCliente();
	}

	public void atenderCliente() {
		quartos.get(quartos.size() - 1).hospedar((hospedes.get(hospedes.size() - 1)));
	}
	public ArrayList<Quarto> getQuartos() {
		return quartos;
	}
}