package entities;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FilaDePessoasBuscandoQuarto {
  private ArrayList<Hospede> listaDePessoasBuscandoQuarto = new ArrayList<Hospede>();
  private ReentrantLock lock = new ReentrantLock();
  private Condition condicaoFilaVazia = lock.newCondition();
  // private Condition condicaoNaoUsandoPop = lock.newCondition();
  // private Boolean usandoOPop = false;


  //   public FilaDePessoasBuscandoQuarto(ArrayList<Hospede> listaDePessoasBuscandoQuarto) {
//     this.listaDePessoasBuscandoQuarto = listaDePessoasBuscandoQuarto;
//   }
    public FilaDePessoasBuscandoQuarto(){
      this.listaDePessoasBuscandoQuarto = new ArrayList<>(); 
    }
    
    public boolean contains(Hospede pessoaBuscandoQuarto){
      return this.listaDePessoasBuscandoQuarto.contains(pessoaBuscandoQuarto);
    }
    public Hospede pop() {
      this.lock.lock();
      // this.usandoOPop = true;
      while(this.size() == 0){
        try{
          condicaoFilaVazia.await();
        } catch (InterruptedException e){
          System.out.println("Interrompida");
        }
      }
      Hospede pessoaBuscandoQuarto = this.listaDePessoasBuscandoQuarto.remove(0);
      System.out.println(pessoaBuscandoQuarto.getName() +" partiu para ser atendida");
      System.out.println("[POP]" +this.toString());
      this.lock.unlock();
      // this.usandoOPop = false;
      // this.condicaoNaoUsandoPop.signalAll();
      return pessoaBuscandoQuarto;

    
  }

  public void removerSemSerDoInicio(Hospede hospedeIndoEmbora){
    this.lock.lock();
    // while(this.usandoOPop){
    //   try{
    //     condicaoNaoUsandoPop.await();
    //   } catch (InterruptedException e){
    //     System.out.println("O que coloca aqui?");
    //   }
    // }
    for (int index = 0; index < this.size(); index++) {
      if(hospedeIndoEmbora == this.listaDePessoasBuscandoQuarto.get(index)){
        this.listaDePessoasBuscandoQuarto.remove(index);
      }
    }
    this.lock.unlock();
  }

  public void push(Hospede Hospede) {
    this.lock.lock();
    // while(this.usandoOPop){
    //   try{
    //     condicaoNaoUsandoPop.await();
    //   } catch (InterruptedException e){
    //     System.out.println("O que coloca aqui?");
    //   }
    // }
    this.listaDePessoasBuscandoQuarto.add(Hospede);
    condicaoFilaVazia.signalAll();
    System.out.println(Hospede.getName() + " entrou na fila de atendimento");
    System.out.println("[PUSH]" + this.toString());
    this.lock.unlock();
    return;
  }

  public Hospede peek(){
    this.lock.lock();

    System.out.println("[PEEK]" + this.toString());
    return this.listaDePessoasBuscandoQuarto.get(0);
  }
  
  public int size(){
    return this.listaDePessoasBuscandoQuarto.size();
  }


  public String toString(){
    String stringFilaDePessoasBuscandoQuarto = "FILA DE PESSOAS BUSCANDO QUARTOS\n";
    if(this.size() == 0){
        return stringFilaDePessoasBuscandoQuarto + "--Não há pessoas na fila de aguardo para os quartos--";
    }
    int i = 1;
    for (Hospede Hospede : listaDePessoasBuscandoQuarto) {
        if(i == this.size()){
            stringFilaDePessoasBuscandoQuarto += Hospede.getName().toString() + ".";
        }
        else{
            stringFilaDePessoasBuscandoQuarto += Hospede.getName().toString() + ",";
        }
        i++;
    }
    return stringFilaDePessoasBuscandoQuarto;
  } 


}