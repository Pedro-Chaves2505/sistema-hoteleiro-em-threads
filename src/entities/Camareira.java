package entities;

public class Camareira extends Thread{
	private String nome;
	private Quarto quartoALavar;

	public Camareira(String nome) {
		super(nome);
		
	}
	public void setQuartoALavar(Quarto quartoALavar) {
		this.quartoALavar = quartoALavar;
	}

	public void arrumarQuarto() {
		this.quartoALavar.usarOQuarto();
	}

	public void run() {
		this.arrumarQuarto();
	}
	public String getNome() {
		return nome;
	}
}
