package entities;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FilaDeRecepcionistas {
  private ArrayList<Recepcionista> listaDeRecepcionistas = new ArrayList<Recepcionista>();
  private ReentrantLock lock = new ReentrantLock();
  private Condition condicaoFilaVazia = lock.newCondition();

  //   public FilaDeRecepcionistas(ArrayList<Recepcionista> listaDeRecepcionistas) {
//     this.listaDeRecepcionistas = listaDeRecepcionistas;
//   }
    public FilaDeRecepcionistas(){
      this.listaDeRecepcionistas = new ArrayList<>(); 
    }
    
    public boolean contains(Recepcionista recepcionista){
      return this.listaDeRecepcionistas.contains(recepcionista);
    }
    public Recepcionista pop() {
      this.lock.lock();
      while(this.size() == 0){
        try{
          condicaoFilaVazia.await();
        } catch (InterruptedException e){
          System.out.println("O que coloca aqui?");
        }
      }
      Recepcionista pessoaBuscandoQuarto = this.listaDeRecepcionistas.remove(0);
      System.out.println(pessoaBuscandoQuarto.getName() +" partiu para ser atendida");
      System.out.println("[POP]" +this.toString());
      this.lock.unlock();
      return pessoaBuscandoQuarto;

    
  }

  public void push(Recepcionista Recepcionista) {
    this.lock.lock();
    this.listaDeRecepcionistas.add(Recepcionista);
    condicaoFilaVazia.signalAll();
    System.out.println(Recepcionista.getName() + " entrou na fila de atendimento");
    System.out.println("[PUSH]" + this.toString());
    this.lock.unlock();
    return;
  }

  public Recepcionista peek(){
    System.out.println("[PEEK]" + this.toString());
    return this.listaDeRecepcionistas.get(0);
  }
  
  public int size(){
    return this.listaDeRecepcionistas.size();
  }


  public String toString(){
    String stringFilaDeRecepcionistas = "FILA DE RECEPCIONISTAS\n";
    if(this.size() == 0){
        return stringFilaDeRecepcionistas + "--Não há recepcionistas disponiveis--";
    }
    int i = 1;
    for (Recepcionista Recepcionista : listaDeRecepcionistas) {
        if(i == this.size()){
            stringFilaDeRecepcionistas += Recepcionista.getName().toString() + ".";
        }
        else{
            stringFilaDeRecepcionistas += Recepcionista.getName().toString() + ",";
        }
        i++;
    }
    return stringFilaDeRecepcionistas;
  } 


}