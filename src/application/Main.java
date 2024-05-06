package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Camareira;
import entities.FilaDePessoasBuscandoQuarto;
import entities.FilaDeQuartosALavar;
import entities.FilaDeQuartosDisponiveis;
import entities.FilaDeRecepcionistas;
import entities.Hospede;
import entities.Quarto;
import entities.Recepcionista;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Camareira;
import entities.FilaDePessoasBuscandoQuarto;
import entities.FilaDeQuartosALavar;
import entities.FilaDeQuartosDisponiveis;
import entities.FilaDeRecepcionistas;
import entities.Hospede;
import entities.Quarto;
import entities.Recepcionista;

public class Main {

	public static void main(String[] args) {
		List<Quarto> quartos = new ArrayList<>();
		FilaDeQuartosALavar filaDeQuartosALavar = new FilaDeQuartosALavar();
		FilaDePessoasBuscandoQuarto filaDePessoasBuscandoQuarto = new FilaDePessoasBuscandoQuarto();
		FilaDeQuartosDisponiveis filaDeQuartosDisponiveis = new FilaDeQuartosDisponiveis();

		List<Hospede> hospedes = new ArrayList<>();
		List<Camareira> camareiras = new ArrayList<>();
		FilaDeRecepcionistas filaDeRecepcionistas = new FilaDeRecepcionistas();
		Random r = new Random();

		System.out.println("aqui" + filaDePessoasBuscandoQuarto);

		for (int i = 0; i < 2; i++) {
			quartos.add(new Quarto(i + 1));
			filaDeQuartosDisponiveis.push(quartos.get(i));
		}

		for (int i = 0; i < 2; i++) {
			Recepcionista recepcionista = new Recepcionista("Recepcionista " +  (i + 1), filaDePessoasBuscandoQuarto, filaDeQuartosDisponiveis, filaDeQuartosALavar, filaDeRecepcionistas);
			filaDeRecepcionistas.push(recepcionista);
			recepcionista.start();
		}

		for (int i = 0; i < 3; i++) {
			hospedes.add(new Hospede("Hospede " + (i + 1),filaDeQuartosALavar, filaDePessoasBuscandoQuarto, filaDeRecepcionistas));
			hospedes.get(i).start();
		}

		

		for (int i = 0; i < 1; i++) {
			camareiras.add(new Camareira("Camareira " + (i + 1), filaDeQuartosALavar,filaDeQuartosDisponiveis));
			camareiras.get(i).start(); 
		}
	}
}