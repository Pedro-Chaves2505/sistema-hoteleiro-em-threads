package main;

import java.util.ArrayList;
import java.util.List;

import entities.Camareira;
import entities.Hospede;
import entities.Quarto;
import entities.Recepcionista;

public class Main {

	public static void main(String[] args) {
		List<Quarto> quartos = new ArrayList<>();
		List<Hospede> hospedes = new ArrayList<>();
		List<Camareira> camareiras = new ArrayList<>();
		List<Recepcionista> recepcionistas = new ArrayList<>();
	
		while (true) {
			for(int i=0;i<1;i++) {
				quartos.add(new Quarto(i+1));
				hospedes.add(new Hospede("Hospede"+(i+1))); 
				camareiras.add(new Camareira("Camareira"+(i+1)));
				recepcionistas.add(new Recepcionista("Recepcionista"+(i+1), hospedes, quartos));
			}
			for(int i=0;i<1;i++) {
				hospedes.get(i).start();
				camareiras.get(i).start();
			}
		
		}
	}
}