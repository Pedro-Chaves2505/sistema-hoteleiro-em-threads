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

		for (int i = 0; i < 10; i++) {
			quartos.add(new Quarto(i + 1));
			hospedes.add(new Hospede("Hospede " + (i + 1),quartosALavar, quartos.get(i)));
			hospedes.get(i).start();
		}
		for (int i = 0; i < 5; i++) {
			camareiras.add(new Camareira("Camareira " + (i + 1), quartosALavar,quartos));
			camareiras.get(i).start(); 
		}
	}
}