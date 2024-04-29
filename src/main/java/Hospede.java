public class Hospede {
  private Quarto quartoOndeHospedado;

  public Hospede(Quarto quarto) {
    this.quartoOndeHospedado = quarto;
  }

  public void usarSeuQuarto() {
    this.quartoOndeHospedado.usarOQuarto();
  }
}