package entities;

import java.util.List;
import java.util.Random;

public class Hospede extends Thread {
	private String nome;
	private Quarto quartoOndeHospedado;
	private Integer grupo;
    private FilaDeQuartosALavar quartosALavar;
    
	public Hospede(String nome, FilaDeQuartosALavar quartosALavar) {
		super(nome);
        this.quartosALavar = quartosALavar;
	}

	public Hospede(String nome, FilaDeQuartosALavar quartosALavar, Quarto quartoOndeHospedado) {
		super(nome);
        this.quartosALavar = quartosALavar;
		this.quartoOndeHospedado=quartoOndeHospedado;
	}
	public void setQuartoOndeHospedado(Quarto quartoOndeHospedado) {
		this.quartoOndeHospedado = quartoOndeHospedado;
	}
	public void setQuarto(Quarto quarto) {
		this.quartoOndeHospedado = quarto;
	}

	public void entrarNoSeuQuarto() {

		
	}

	public Integer reduzirGrupo(Integer redutor) {
		grupo = grupo - redutor;
		return grupo - redutor;
	}
	public Integer getGrupo() {
		if(grupo ==null){
			return 0;
		}
		return grupo;
	}

    public Quarto getQuarto(){
        return this.quartoOndeHospedado;
    }

	public void run() {
		Random r = new Random();
		int numeroDeVezesQueVaiEntrarESair = r.nextInt(10);
        int i = 0;
        while(i<numeroDeVezesQueVaiEntrarESair){
			while(this.quartosALavar.contains(quartoOndeHospedado)) {
				try {
					System.out.println(getName() + " esperando a chave estar na recepção...");
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            this.quartoOndeHospedado.entrarNoQuarto(getName());
            if(!this.quartosALavar.contains(this.getQuarto())){
                this.quartosALavar.push(this.getQuarto());
            }            
            try {
				sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            finally{
                i++;
            }      
        }
	}
	public String getNome() {
		return nome;
	}
}