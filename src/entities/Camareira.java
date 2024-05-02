package entities;

import java.util.List;

public class Camareira extends Thread{
	private String nome;
	private List<Quarto> quartosDisponiveis;
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
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.arrumarQuarto();
		if(quartoALavar.get(0).getSendoUsado()){
			this.quartoALavar.remove(0);
		}else {
			this.quartosDisponiveis.add(quartoALavar.get(0));
			this.quartoALavar.remove(0);
		}
		
	}
	public String getNome() {
		return nome;
	}
}
