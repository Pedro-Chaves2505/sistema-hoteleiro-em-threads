// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) {
    Quarto quarto = new Quarto();
    Hospede hospede = new Hospede(quarto);
    hospede.usarSeuQuarto();
    
  }

  // @Test
  // void addition() {
  //     assertEquals(2, 1 + 1);
  // }
}