package entities;

import java.util.List;
import java.util.Random;

public class Hospede extends Thread {
	private String nome;
	private Quarto quartoOndeHospedado;
	private Integer grupo = 12;
    private FilaDeQuartosALavar quartosALavar;
	private FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto;
	private FilaDeRecepcionistas filaDeRecepcionistas;
    
	public Hospede(String nome, FilaDeQuartosALavar quartosALavar, FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto, FilaDeRecepcionistas filaDeRecepcionistas, int grupo) {
		super(nome);
        this.quartosALavar = quartosALavar;
		this.filaDePessoasBuscandoQuarto = filaDePessoasBuscandoQuarto;
		this.filaDeRecepcionistas = filaDeRecepcionistas;
		this.grupo = grupo;
	}

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

	public Integer reduzirGrupo() {
		this.grupo = this.grupo - 4;
		return this.grupo;
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
        int i = 0;
		int j = 0;


		// while(this.getGrupo() > 4){
		// 	int resto = this.reduzirGrupo();
		// 	(new Hospede("Hospede dependente", quartosALavar, quartoOndeHospedado, resto)).start();
		// }

		System.out.println(this.filaDePessoasBuscandoQuarto);
		
		this.filaDePessoasBuscandoQuarto.push(this);

		while(this.quartoOndeHospedado == null && j<2){
			try {
				System.out.println(getName() + " Aguardando ter um quarto...");
				sleep(3000);
				j++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(j == 2){
			System.out.println("Indo embora!");
			this.filaDePessoasBuscandoQuarto.removerSemSerDoInicio(this);
			return;
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