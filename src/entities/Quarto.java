package entities;

import java.util.concurrent.locks.ReentrantLock;

public class Quarto {
	private Integer numeroLimiteDePessoas= 4;
	private Boolean temAlguemDentroDele = false;
	
	private Hospede hospede;
	private Integer numeroDeHospedes;
	
	private Recepcionista recepcionista;
	private ReentrantLock lock = new ReentrantLock();
	private Integer numero;
	private Boolean sendoUsado=false;
	
	public Quarto(Integer numero) {
		this.numero =numero;
	}
	
	public String usarOQuarto() {
		lock.lock();
		sendoUsado = true;
		if (temAlguemDentroDele) {
			System.out.println("!!!!!!!Uso simultaneo!");
			return "===uso simultaneo===";
		}
		this.temAlguemDentroDele = true;
		System.out.println("Estou dentro do quarto usando ele!");
		System.out.println("Estou saindo do quarto!");
		this.temAlguemDentroDele = false;
		sendoUsado = false;
		lock.unlock();

		return "uso normal";
	}
	public void setNumeroDeHospedes(Integer numeroDeHospedes) {
		this.numeroDeHospedes = numeroDeHospedes;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Hospede getHospede() {
		return this.hospede;
	}

	public Quarto(int numero) {
		this.numero = numero;
	}
	public void setRecepcionista(Recepcionista recepcionista) {
		this.recepcionista = recepcionista;
	}
	public Integer getNumeroLimiteDePessoas() {
		return numeroLimiteDePessoas;
	}
	public Integer getNumero() {
		return numero;
	}
	public Recepcionista getRecepcionista() {
		return recepcionista;
	}
	public Boolean getSendoUsado() {
		return sendoUsado;
	}
	
}