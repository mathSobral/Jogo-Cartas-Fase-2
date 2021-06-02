package com.uefs.br.jogopaciencia.models;

import java.util.Random;

public class Baralho {
	private Carta[] cartas;
	private int cartasRestantes;
	private Random gerador;
	private boolean duplo;
	
	public Baralho(boolean duplo) {
		gerador = new Random();
		cartasRestantes = 0;
		cartas = new Carta[52];
		this.duplo = duplo;
		gerarCartas();
	}
	
	public void resetar() {
		cartasRestantes = 52;
	}
	
	public Carta getCarta() {
		if(!temMaisCartas()) {
			if(duplo) {
				duplo = false;
				gerarCartas();
			}
		}
		
		int random = gerador.nextInt(cartasRestantes);
		Carta carta = cartas[random];
		swap(random, cartasRestantes - 1);
		cartasRestantes--;
		return carta;
	}
	
	public boolean temMaisCartas() {
		return cartasRestantes > 0;
	}
	
	private void swap(int i, int j) {
		Carta aux = cartas[i];
		cartas[i] = cartas[j];
		cartas[j] = aux;
	}
	
	public void gerarCartas() {
		String naipes[] = {"ESPADAS", "COPAS", "PAUS", "OURO"};
		Integer numeros[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		
		for(String naipe : naipes)
			for(Integer numero : numeros)
				cartas[cartasRestantes++] = new Carta(numero, naipe);
			
	}
}