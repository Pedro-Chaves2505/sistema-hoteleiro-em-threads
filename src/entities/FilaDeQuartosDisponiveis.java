package entities;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.PriorityBlockingQueue; 


public class FilaDeQuartosDisponiveis {
  private ArrayList<Quarto> listaDeQuartosDisponiveis = new ArrayList<Quarto>();
  private ReentrantLock lock = new ReentrantLock();
  private Condition condicaoFilaVazia = lock.newCondition();

  //   public FilaDeQuartosDisponiveis(ArrayList<Quarto> listaDeQuartosDisponiveis) {
//     this.listaDeQuartosDisponiveis = listaDeQuartosDisponiveis;
//   }
    public FilaDeQuartosDisponiveis(){
      this.listaDeQuartosDisponiveis = new ArrayList<>(); 
    }
    
    public boolean contains(Quarto quarto){
      return this.listaDeQuartosDisponiveis.contains(quarto);
    }
    public Quarto pop() {
      this.lock.lock();
      while(this.size() ==0){
        try{
          condicaoFilaVazia.await();
        } catch (InterruptedException e){
          System.out.println("Em " + this + " houve exceção.");
        }
      }
      Quarto quartoDisponiveis = this.listaDeQuartosDisponiveis.remove(0);
      System.out.println("Chave do quarto"+ quartoDisponiveis.getNumero() +" retirada da fila de quartos disponiveis");
      System.out.println("[POP]" +this.toString());
      this.lock.unlock();
      return quartoDisponiveis;

    
  }

  public void push(Quarto quarto) {
    this.lock.lock();
    this.listaDeQuartosDisponiveis.add(quarto);
    condicaoFilaVazia.signalAll();
    System.out.println("Chave do quarto " + quarto.getNumero() + " entregue para a fila de quartos disponiveis");
    System.out.println("[PUSH]" + this.toString());
    this.lock.unlock();
    return;
  }
  public Quarto peek(){
    System.out.println("[PEEK]" + this.toString());
    return this.listaDeQuartosDisponiveis.get(0);
  }
  
  public int size(){
    return this.listaDeQuartosDisponiveis.size();
  }


  public String toString(){
    String stringFilaDeQuartosDisponiveis = "LISTA DE QUARTOS A DISPONIVEIS\n";
    if(this.size() == 0){
        return stringFilaDeQuartosDisponiveis + "--Não há quartos disponiveis--";
    }
    int i = 1;
    for (Quarto quarto : listaDeQuartosDisponiveis) {
        if(i == this.size()){
            stringFilaDeQuartosDisponiveis += quarto.getNumero().toString() + ".";
        }
        else{
            stringFilaDeQuartosDisponiveis += quarto.getNumero().toString() + ",";
        }
        i++;
    }
    return stringFilaDeQuartosDisponiveis;
  } 


}