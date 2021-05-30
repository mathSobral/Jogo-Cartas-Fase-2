package com.uefs.br.jogopaciencia.gui;

public class Selecao {
	private int inicio;
	private int fim;

	public Selecao() {
		resetar();
	}

	
	public int getInicio() {
		return inicio;
	}
	
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	
	public int getFim() {
		return fim;
	}
	
	public void setFim(int fim) {
		this.fim = fim;
	}

	public void adicionarIndice(int indice) {

		if(inicio == -1) {
			inicio = indice;
			fim = indice;
		}
		else {
			if(indice < inicio) 
				resetar();
			else if(inicio == indice)
				resetar();
			else if(indice != inicio)
					fim = indice;
			
				
		}
	}
	
	
	public void resetar() {
		inicio = -1;
		fim = -1;
	}
	

	public boolean possuiFaixaSelecao() {
		return fim != inicio;
	}
	
	public boolean possuiSelecao() {
		return inicio != -1;
	}

	public int getNumeroDeItensSelecionados() {
		if(fim == inicio)
			return 1;
		
		return fim - inicio + 1 ;
	}
}
