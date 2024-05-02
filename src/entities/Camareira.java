package entities;

import java.util.List;

public class Camareira extends Thread{
	private String nome;
	private List<Quarto> quartoALavar;

	public Camareira(String nome,List<Quarto> quartos) {
		super(nome);
		quartoALavar = quartos;
	}
	public void setQuartoALavar(List<Quarto> quartoALavar) {
		this.quartoALavar = quartoALavar;
	}

	public void arrumarQuarto() {
		this.quartoALavar.get(0).usarOQuarto();
	}

	public void run() {
		while(quartoALavar ==null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.arrumarQuarto();
		this.quartoALavar.remove(0);
	}
	public String getNome() {
		return nome;
	}
}
