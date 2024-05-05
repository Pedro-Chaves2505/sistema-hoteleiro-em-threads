package entities;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FilaClientesParaAtender {
    private ReentrantLock lock = new ReentrantLock();
    private List<Cliente> listaClientesParaAtender = new ArrayList<Cliente>();
   private List <Quarto> listaQuartosDisponiveis = new ArrayList<Quarto>();
    //private List <Quarto> listaQuartosSendoUsados = new ArrayList<Quarto>();
   // public List<Quarto> quartos ;
// POSTERIOMENTE UM OBJETO CLIENTE TEM QUE PASSAR A SER UM OBJETO HOSPEDE
   
 

// CONSTRUTOR DA CLASSE

public void FilaClientesParaAtender( List <Quarto> listaQuartosDisponiveis){
  this.listaQuartosDisponiveis = listaQuartosDisponiveis;
  }


// os quartos disponiveis são aqueles  que noa estao atrelados a nenhum hospede


// METODO PARA CLIENTES TENTAREM ENTRAR NA LISTA  listaClientesParaAtender
public void push(Cliente cliente) {
  this.lock.lock();
  try{
   this.listaClientesParaAtender.add(cliente);
   System.out.println("Cliente " +cliente.getNome() + " prestes a ser atendido");
    System.out.println("[PUSH]" + this.toString());
  }finally{
    this.lock.unlock();
  }
  
  return;
}

// veio de recepcionista
public List<Quarto> getListaQuartosDisponiveis() {
  return listaQuartosDisponiveis;
}



// NA VERDADE, O CLIENTE ESTÁ SENDO ATENDIDO AQUI E NÃO MAIS EM ATENDERCLIENTE

  public Cliente pop(Recepcionista recepcionista) { // quando for chamar esse metedo vou ter que passar o recepcionsita que irá fazer esse pop
    this.lock.lock();
    Cliente clienteAHospedar = this.listaClientesParaAtender.remove(0); // se eu tirar o cliente dessa lista, quer dizer que ele foi atendido
    Quarto quartoAEntregarAChave = listaQuartosDisponiveis.remove(0) ;// então aquele quarto que foi entregue ao cliente não está mais disponível, então sai da ListadeQuartosDisponiveis
    recepcionista.hospedar(quartoAEntregarAChave, clienteAHospedar);    
       // Criar uma instância de FilaDeQuartosALavar
       FilaDeQuartosALavar filaDeQuartosALavar = new FilaDeQuartosALavar();
       // Adicionar o quarto à lista de quartos a serem limpos usando o método push()
       filaDeQuartosALavar.push(quartoAEntregarAChave);
  
    System.out.println("Cliente" + clienteAHospedar.getNome()+ " atendido e está no quarto "+quartoAEntregarAChave+" ,portanto retirado da fila de clientes para atender");
    System.out.println("[POP]" +this.toString());
    this.lock.unlock();
    return clienteAHospedar;

    
}











}