// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) {
    Quarto quarto = new Quarto();
    while(true){
      Hospede hospede1 = new Hospede(quarto, 1);
      
      Camareira camareira1 = new Camareira(quarto, 1);
      hospede1.start();
      camareira1.start();
      
    }
    

    
  }

  // @Test
  // void addition() {
  //     assertEquals(2, 1 + 1);
  // }
}