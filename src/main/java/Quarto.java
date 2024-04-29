public class Quarto{
  private Boolean temAlguemDentroDele = false;

  public String usarOQuarto(){
    if(temAlguemDentroDele){
      System.out.println("!!!!!!!Uso simultaneo!");
      return "===uso simultaneo===";
    }
    this.temAlguemDentroDele = true;
    // System.out.println("Estou dentro do quarto usando ele!");
    // System.out.println("Estou saindo do quarto!");
    this.temAlguemDentroDele = false;
    return "uso normal";
  }
  
}  