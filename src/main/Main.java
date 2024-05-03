package main;

import java.util.ArrayList;
import java.util.List;

import entities.Camareira;
import entities.FilaDeQuartosALavar;
import entities.Hospede;
import entities.Quarto;
import entities.Recepcionista;

public class Main {

	public static void main(String[] args) {
		List<Quarto> quartos = new ArrayList<>();
		FilaDeQuartosALavar quartosALavar = new FilaDeQuartosALavar();
		List<Hospede> hospedes = new ArrayList<>();
		List<Camareira> camareiras = new ArrayList<>();
		List<Recepcionista> recepcionistas = new ArrayList<>();


		// criando recepcionistas. Mínimo 5
		for (int i = 0; i<10; i++){
			recepcionistas.add(new Recepcionista("Recepcionista " + (i+1), hospedes, quartos)); // lista recepcionista
		  }



        // Criando quartos e quartosALavar
		for (int i = 0; i < 10; i++) {
			quartos.add(new Quarto(i + 1,recepcionistas.get(i))); // lista quartos
			quartosALavar.push(new Quarto(1,recepcionistas.get(i)));
			
		}

		//Criando HOSPEDE e CAMAREIRA 
		for(int i =0;i<10;i++){
            hospedes.add(new Hospede("Hospede " + (i + 1),quartosALavar, quartos.get(0))); // lita hospedes
			camareiras.add(new Camareira("Camareira " + (i + 1), quartosALavar,quartos));
		}

		

		
		  // Iniciando as threads dos recepcionistas
		  for (Recepcionista recepcionista : recepcionistas) {
			recepcionista.start();
		}
	
		// Chama o método atenderCliente depois que todas as threads forem iniciadas
		for (Recepcionista recepcionista : recepcionistas) {
			recepcionista.atenderCliente();
		}

		
	    // Iniciando threads dos hóspedes
		for (Hospede hospede : hospedes) {
			hospede.start();
		}

		// Iniciando threads das camareiras
		for (Camareira camareira : camareiras) {
			camareira.start();
		}
			
				
				

    






	}
}