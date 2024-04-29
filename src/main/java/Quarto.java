public class Quarto{
  private Boolean temAlguemDentroDele;

  public void usarOQuarto(){
    this.temAlguemDentroDele = true;
    System.out.println("Estou dentro do quarto usando ele!");
    System.out.println("Estou saindo do quarto!");
    this.temAlguemDentroDele = false;
  }
  
}  