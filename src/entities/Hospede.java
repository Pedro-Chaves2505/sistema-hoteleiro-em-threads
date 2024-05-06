package entities;

import java.util.List;
import java.util.Random;

public class Hospede extends Thread {
	private String nome;
	private Quarto quartoOndeHospedado;
	private Integer grupo;
	private Integer tolerancia;
    private FilaDeQuartosALavar quartosALavar;

	public FilaDeQuartosALavar getQuartosALavar() {
		return quartosALavar;
	}
    
	public Hospede(String nome, FilaDeQuartosALavar quartosALavar) {
		super(nome);
        this.quartosALavar = quartosALavar;
	}

	public Hospede(String nome, Integer grupo,FilaDeQuartosALavar quartosALavar) {
		super(nome);
		this.grupo = grupo;
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
		// visa reduzir o numero do grupo PARA O NÚMERO DO REDUTOR, dessa forma
		// se o grupo for 4 e o redutor for 2, o grupo será 2 e será retornado o numero o novo número de remanescentes

		// remanescentes é uma variável local(Work in Progress) que será implementada futuramente
		Integer remanescentes = this.grupo - redutor;
		this.grupo = redutor;
		return remanescentes; // retornando remanescentes visto que o novo numero do grupo pode ser acessado com um simples <hospede>.getGrupo()
	}
	public Integer getGrupo() {
		return grupo;
	}

	public void darUmaVolta() {
		System.out.println(this.nome+" foi dar uma volta!");
		Thread.sleep(1000);
	}

	public void grieve() {
		List<String> insults = new ArrayList<String>();
		insults.add(" nem queria um lugar mesmo.");
		insults.add(" diz que a concorrência é melhor.");
		insults.add(" entende o problema, mas não deseja mais um quarto.");
		insults.add(" disse que vai voltar para a itália.");
		insults.add(" ameaçou reportar o hotel para o inspetor sanitário local!");
		insults.add(" tentou subornar um dos recepcionistas, sem sucesso.");
		insults.add(" era o presidente, que lástima.");
		insults.add(" era filho do chefe, foi bom trabalhar com vocês.");
		Random insult = new Random();
		System.out.println(this.nome+insults.get(insult.nextInt(insults.size())));
	}

  public Quarto getQuarto(){
    return this.quartoOndeHospedado;
  }

	public void run() {
		if(QuartoOndeHospedado==null) {
			if(!Recepcionista.roomless.contains(this)) {
				boolean inQueue = Recepcionista.lodgeRequest(this);
				if(!inQueue) {
					this.tolerancia--;
					if(tolerancia>0) {
						darUmaVolta();
					} else {
						grieve();
					}
				}
			}
		} else {
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
	}
	public String getNome() {
		return nome;
	}
}