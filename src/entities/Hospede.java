package entities;

import java.util.List;

public class Hospede extends Thread {
	private String nome;
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
	
	// metodo setQuarto aqui

	public void entrarNoSeuQuarto() {

		
	}

    // metodo reduzirGrupo aqui
    
	// metodo get grupo aqui
	
    public Quarto getQuarto(){
        return this.quartoOndeHospedado;
    }

	public void run() {
        int i = 0;
        while(i<5){
            this.quartoOndeHospedado.entrarNoQuarto(getName());
            if(!this.quartosALavar.contains(this.getQuarto())){
                this.quartosALavar.push(this.getQuarto());
            }            this.quartosALavar.peek();
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