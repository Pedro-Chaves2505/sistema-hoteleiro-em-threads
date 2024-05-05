package main;

import java.util.ArrayList;
import java.util.List;

import entities.Camareira;
import entities.Cliente;
import entities.FilaClientesParaAtender;
import entities.FilaDeQuartosALavar;
import entities.Hospede;
import entities.Quarto;
import entities.Recepcionista;

public class Main {

	public static void main(String[] args) {
		List <Quarto> listaQuartosDisponiveis = new ArrayList<Quarto>();
		FilaDeQuartosALavar quartosALavar = new FilaDeQuartosALavar();
		FilaClientesParaAtender clientesAtender = new FilaClientesParaAtender();
		List<Hospede> hospedes = new ArrayList<>();
		List<Camareira> camareiras = new ArrayList<>();
		List<Recepcionista> recepcionistas = new ArrayList<>();
		List<Cliente> clientes = new ArrayList<>();

		
		for (int i = 0; i < 3; i++) { // // MODIFICADO PARA O TESTE RECEPCIONISTA - HOSPEDE
			listaQuartosDisponiveis.add(new Quarto(i + 1));
			hospedes.add(new Hospede("Hospede " + (i + 1),quartosALavar, listaQuartosDisponiveis.get(i)));
			camareiras.add(new Camareira("Camareira " + (i + 1), quartosALavar,listaQuartosDisponiveis));
			clientes.add(new Cliente("Cliente" + (i+1),clientesAtender));
		    recepcionistas.add(new Recepcionista("Recepcionsita " + (i+1), hospedes,clientesAtender));
			recepcionistas.get(i).start();
			hospedes.get(i).start();
			camareiras.get(i).start(); 
			
		}

		//for(hospede : hospedes){
			//recepcionista.atendercliente
		}
	}
