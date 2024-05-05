package entities;

public class Cliente  extends Thread{

    private String nome;
    private FilaClientesParaAtender tentandoConseguirQuarto;

    public Cliente(String nome,FilaClientesParaAtender tentandoConseguirQuarto ){
        this.nome = nome;
        this.tentandoConseguirQuarto = tentandoConseguirQuarto;
    }




    public String getNome(){
        return nome;
    }

    public void run(){
       this.tentandoConseguirQuarto.

    

    }

}
