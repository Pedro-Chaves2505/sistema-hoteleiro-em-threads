package entities;

import java.util.List;
import java.util.Random;

public class Hospede extends Thread {
	private String nome;
	private Quarto quartoOndeHospedado;
	private Integer grupo;
    private FilaDeQuartosALavar quartosALavar;
	private FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto;
	private FilaDeRecepcionistas filaDeRecepcionistas;
    
	public Hospede(String nome, FilaDeQuartosALavar quartosALavar, FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto, FilaDeRecepcionistas filaDeRecepcionistas) {
		super(nome);
        this.quartosALavar = quartosALavar;
		this.filaDePessoasBuscandoQuarto = filaDePessoasBuscandoQuarto;
		this.filaDeRecepcionistas = filaDeRecepcionistas;
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

	public void pedirSaidaDeQuarto() {
		filaDeRecepcionistas.pop().retirarPessoaDeQuarto(this);
	}

	public Integer reduzirGrupo(Integer redutor) {
		grupo = grupo - redutor;
		return grupo - redutor;
	}
	public Integer reduzirGrupo(Integer redutor) {
		grupo = grupo - redutor;
		return grupo - redutor;
	}
	public Integer getGrupo() {
		return grupo;
	}

    public Quarto getQuarto(){
        return this.quartoOndeHospedado;
    }

	public void run() {
		Random r = new Random();
		int numeroDeVezesQueVaiEntrarESair = r.nextInt(10);
        int i = 0, j = 0;

		System.out.println(this.filaDePessoasBuscandoQuarto);
		
		this.filaDePessoasBuscandoQuarto.push(this);

		while(this.quartoOndeHospedado == null){
			try {
				System.out.println(getName() + " Aguardando ter um quarto...");
				sleep(3000);
				j++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		this.pedirSaidaDeQuarto();
	}
	public String getNome() {
		return nome;
	}
}