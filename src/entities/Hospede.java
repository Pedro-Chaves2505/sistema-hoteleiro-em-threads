package entities;

public class Hospede extends Thread {
	private String nome;
	private Quarto quartoOndeHospedado;
	private Integer grupo;

	public Hospede(String nome) {
		super(nome);
	}
	public Hospede(String nome,Quarto quartoOndeHospedado) {
		super(nome);
		this.quartoOndeHospedado=quartoOndeHospedado;
	}
	public void setQuartoOndeHospedado(Quarto quartoOndeHospedado) {
		this.quartoOndeHospedado = quartoOndeHospedado;
	}
	public void setQuarto(Quarto quarto) {
		this.quartoOndeHospedado = quarto;
	}

	public void usarSeuQuarto() {
		this.quartoOndeHospedado.usarOQuarto(getName());
	}
	public Integer reduzirGrupo(Integer redutor) {
		grupo = grupo - redutor;
		return grupo - redutor;
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