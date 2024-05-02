package entities;

public class Hospede extends Thread {
	private String nome;
	private Quarto quartoOndeHospedado;
	private Integer grupo;

	public Hospede(String nome) {
		super(nome);
	}
	public void setQuartoOndeHospedado(Quarto quartoOndeHospedado) {
		this.quartoOndeHospedado = quartoOndeHospedado;
	}
	public void setQuarto(Quarto quarto) {
		this.quartoOndeHospedado = quarto;
	}

	public void usarSeuQuarto() {
		this.quartoOndeHospedado.usarOQuarto();
	}
	public Integer getGrupo() {
		return grupo;
	}
	public void run() {
		this.usarSeuQuarto();
	}
	public String getNome() {
		return nome;
	}
}
