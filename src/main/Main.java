package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Camareira;
import entities.FilaDePessoasBuscandoQuarto;
import entities.FilaDeQuartosALavar;
import entities.FilaDeQuartosDisponiveis;
import entities.Hospede;
import entities.Quarto;
import entities.Recepcionista;

public class Main {

	public static void main(String[] args) {
		List<Quarto> quartos = new ArrayList<>();
		FilaDeQuartosALavar quartosALavar = new FilaDeQuartosALavar();
		FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto = new FilaDePessoasBuscandoQuarto();
		FilaDeQuartosDisponiveis filaDeQuartosDisponiveis = new FilaDeQuartosDisponiveis();

		List<Hospede> hospedes = new ArrayList<>();
		List<Camareira> camareiras = new ArrayList<>();
		List<Recepcionista> recepcionistas = new ArrayList<>();
		Random r = new Random();

		System.out.println("aqui" + filaDePessoasBuscandoQuarto);

		for (int i = 0; i < 5; i++) {
			quartos.add(new Quarto(i + 1));
			filaDeQuartosDisponiveis.push(quartos.get(i));
		}

		for (int i = 0; i < 10; i++) {
			hospedes.add(new Hospede("Hospede " + (i + 1),quartosALavar, filaDePessoasBuscandoQuarto));
			hospedes.get(i).start();
		}

		for (int i = 0; i < 10; i++) {
			recepcionistas.add(new Recepcionista("Recepcionista " +  (i + 1), filaDePessoasBuscandoQuarto, filaDeQuartosDisponiveis));
			recepcionistas.get(i).start();
		}

		for (int i = 0; i < 5; i++) {
			camareiras.add(new Camareira("Camareira " + (i + 1), quartosALavar,quartos));
			camareiras.get(i).start(); 
		}
	}
}