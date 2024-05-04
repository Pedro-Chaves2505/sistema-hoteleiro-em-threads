package entities;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Quarto {
	private Integer numeroLimiteDePessoas= 4;
	private Boolean temAlguemDentroDele = false;
	private boolean chave = false;
	
	private Hospede hospede;
	private Integer numeroDeHospedes;
	
	private Recepcionista recepcionista;
	private ReentrantLock lock = new ReentrantLock();
	private Integer numero;
	private Boolean sendoUsado=false;
	
	public Quarto(Integer numero,Recepcionista recepcionista) {
		this.numero =numero;
		this.recepcionista = recepcionista;
	}
	
	public void entrarNoQuarto(String nome) {
		Random r = new Random();
		int tempoDentroDoQuarto = r.nextInt(5);
		int THOUSAND_MILISSECONDS = 1000;
		try{
			System.out.println(nome + " tentando entrar no quarto " + this.getNumero());
			this.lock.lock();
			System.out.println(lock.toString());
			if(this.temAlguemDentroDele){
				System.out.println("Entrada simultanea!");
			}
			sendoUsado = true;
			this.temAlguemDentroDele = true;
			System.out.println(nome +" esta dentro do quarto " + this.getNumero() +  " usando ele!");
			try{
				Thread.sleep(tempoDentroDoQuarto * THOUSAND_MILISSECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.println(nome +" esta saindo do quarto  " + this.getNumero() +  "!");
			this.temAlguemDentroDele = false;
			sendoUsado = false;
		}
		finally{
			this.lock.unlock();
			System.out.println(lock.toString());

		}
		

	}

	public Integer getNumeroLimiteDePessoas() {
		return numeroLimiteDePessoas;
	}

	public void setNumeroLimiteDePessoas(Integer numeroLimiteDePessoas) {
		this.numeroLimiteDePessoas = numeroLimiteDePessoas;
	}

	public Boolean getTemAlguemDentroDele() {
		return temAlguemDentroDele;
	}

	public void setTemAlguemDentroDele(Boolean temAlguemDentroDele) {
		this.temAlguemDentroDele = temAlguemDentroDele;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Integer getNumeroDeHospedes() {
		return numeroDeHospedes;
	}

	public void setNumeroDeHospedes(Integer numeroDeHospedes) {
		this.numeroDeHospedes = numeroDeHospedes;
	}

	public Recepcionista getRecepcionista() {
		return recepcionista;
	}

	public void setRecepcionista(Recepcionista recepcionista) {
		this.recepcionista = recepcionista;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getSendoUsado() {
		return sendoUsado;
	}

	public void setSendoUsado(Boolean sendoUsado) {
		this.sendoUsado = sendoUsado;
	}



	
	
}