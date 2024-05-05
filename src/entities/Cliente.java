package entities;

public class Cliente  extends Thread{
    private Integer grupo;
    private String nome;
    private Quarto quartoOndeHospedado;
    private FilaClientesParaAtender tentandoConseguirQuarto;

    public Cliente(String nome,FilaClientesParaAtender tentandoConseguirQuarto ){
        this.nome = nome;
        this.tentandoConseguirQuarto = tentandoConseguirQuarto;
    }




    public String getNome(){
        return nome;
    }

    public void run(){
      this.tentandoConseguirQuarto.push(this);
    }

    public Integer getGrupo() { // metodo getGrupo veio da classe hospede
		return grupo;
	}


    public Integer reduzirGrupo(Integer redutor) { //metodo reduzirGrupo veio da classe hospede
		grupo = grupo - redutor; 
		return grupo - redutor;
	}



    public void setQuarto(Quarto quarto) { // metodo setQuarto veio da classe hospede
		this.quartoOndeHospedado = quarto;
	}
    
}
