package com.uefs.br.jogopaciencia.main;

import com.uefs.br.jogopaciencia.models.Baralho;
import com.uefs.br.jogopaciencia.models.NoCarta;

public class Main {

	public static void main(String[] args) {
		Baralho baralho = new Baralho();
		
		baralho.embaralhar();
		NoCarta carta;
		int i = 1;
		while((carta = baralho.retiraCartaTopo()) != null) {
			System.out.println(i++ + " - "+ carta );
		}
		baralho.embaralhar();
		while((carta = baralho.retiraCartaTopo()) != null) {
			System.out.println(i++ + " - "+ carta );
		}
	}

}
