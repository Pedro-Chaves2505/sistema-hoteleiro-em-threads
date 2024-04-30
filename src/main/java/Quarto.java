//import java.util.concurrent.locks.Lock;

public class Quarto{
  private Boolean temAlguemDentroDele = false;
 // private Lock lock = new Lock();

  public String usarOQuarto(){
    
    if(temAlguemDentroDele){
      System.out.println("!!!!!!!Uso simultaneo!");
      return "===uso simultaneo===";
    }
    this.temAlguemDentroDele = true;
    System.out.println("Estou dentro do quarto usando ele!");
    System.out.println("Estou saindo do quarto!");
    this.temAlguemDentroDele = false;
    return "uso normal";
  }
  
}  