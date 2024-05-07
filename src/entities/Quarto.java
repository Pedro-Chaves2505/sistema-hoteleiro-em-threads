package entities;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Quarto {
	private Hospede hospede;
	private Integer numeroDeHospedes;
	
	private Recepcionista recepcionista;
	private ReentrantLock lock = new ReentrantLock();
	private Integer numero;
	
	public Quarto(Integer numero) {
		this.numero =numero;
	}
	
	public void entrarNoQuarto(String nome) {
		Random r = new Random();
		int tempoDentroDoQuarto = r.nextInt(5);
		int THOUSAND_MILISSECONDS = 1000;
		try{
			System.out.println(nome + " tentando entrar no quarto " + this.getNumero());
			this.lock.lock();
			System.out.println(lock.toString());
			
			System.out.println(nome +" esta dentro do quarto " + this.getNumero() +  " usando ele!");
			try{
				Thread.sleep(tempoDentroDoQuarto * THOUSAND_MILISSECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.println(nome +" esta saindo do quarto  " + this.getNumero() +  "!");
		}
		finally{
			this.lock.unlock();
			System.out.println(lock.toString());

		}
	}

	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.lock.lock();
		this.hospede = hospede;
		this.lock.unlock();
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

	public Integer getNumero() {
		return numero;
	}
}