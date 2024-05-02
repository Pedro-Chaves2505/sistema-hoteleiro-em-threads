import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {

    ArrayList<Quarto> listaDeQuartosALavar = new ArrayList<>();
    
    for(int i = 0; i < 10; i++){
      Quarto quarto = new Quarto();
      listaDeQuartosALavar.add(quarto);
    }

    FilaDeQuartosALavar filaDeQuartosALavar = new FilaDeQuartosALavar(listaDeQuartosALavar);

    for(int i = 0; i < 10; i++){
      Camareira camareira1 = new Camareira(filaDeQuartosALavar, 1);
      camareira1.start();
    }
    
    
  }
}