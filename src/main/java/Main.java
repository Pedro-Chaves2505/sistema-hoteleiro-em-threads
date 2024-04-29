// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) {
    Quarto quarto = new Quarto();
    int execucao = 0;
    while(true){
      Hospede hospede1 = new Hospede(quarto, 1);
      Hospede hospede2 = new Hospede(quarto, 2);
      Hospede hospede3 = new Hospede(quarto, 3);
      Hospede hospede4 = new Hospede(quarto, 4);
      hospede1.start();
      hospede2.start();
      hospede3.start();
      hospede4.start();
      execucao++;
    }
    

    
  }

  // @Test
  // void addition() {
  //     assertEquals(2, 1 + 1);
  // }
}