package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FilaProdutorConsumidorNaoLimitada<T> {
  private List<T> listaDeElementos = new ArrayList<T>();
  private ReentrantLock lock = new ReentrantLock();
  private Condition condicaoDePilhaVazia = lock.newCondition();

  //   public FilaDeTsALavar(ArrayList<T> listaDeElementos) {
//     this.listaDeElementos = listaDeElementos;
//   }

    
    public boolean contains(T elemento){
      return this.listaDeElementos.contains(elemento);
    }
    public T pop() {
        try{
            this.lock.lock();
            while(this.size() == 0){
                try{
                    condicaoDePilhaVazia.await();
                } catch(InterruptedException e) {
                    System.out.println("O que coloco aqui?");
                }
            }
            T elementoRemovido = this.listaDeElementos.remove(0);
            return elementoRemovido;
        }
        finally{
            this.lock.unlock();
        }     
  }

  public void push(T T) {
    this.lock.lock();
    this.listaDeElementos.add(T);
    condicaoDePilhaVazia.signalAll();
    this.lock.unlock();
    return;
  }
  public T peek(){
    System.out.println("[PEEK]" + this.toString());
    return this.listaDeElementos.get(0);
  }
  
  public int size(){
    return this.listaDeElementos.size();
  }

  public List getList(){
    return this.listaDeElementos;
  }

  public String toString(){
    String stringFilaDeTsALavar= "";
    if(this.size() == 0){
        return "--Pilha vazia--";
    }
    int i = 1;
    for (T elemento : listaDeElementos) {
        if(i == this.size()){
            stringFilaDeTsALavar += elemento.toString() + ".";
        }
        else{
            stringFilaDeTsALavar += elemento.toString() + ",";
        }
        i++;
    }
    return stringFilaDeTsALavar;
  } 


}