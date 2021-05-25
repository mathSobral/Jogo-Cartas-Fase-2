package com.uefs.br.jogopaciencia.gui;

public class Movimento {
	private int pilhaOrigem;
	private int pilhaDestino;
	private int quantidade;
	
	public int getPilhaOrigem() {
		return pilhaOrigem;
	}
	
	public int getPilhaDestino() {
		return pilhaDestino;
	}
	

	public int getQuantidade() {
		return quantidade;
	}
	
	public boolean movimentoFormado() {
		return pilhaOrigem != 0 && pilhaDestino != 0;
	}
	
	public void adicionarPilha(int numeroPilha, int quantidade) {
		if(pilhaOrigem == 0) {
			pilhaOrigem = numeroPilha;
			this.quantidade = quantidade;
		}
		else {
			if(numeroPilha != pilhaOrigem)
				pilhaDestino = numeroPilha;
		}
	}

	public void resetar() {
		pilhaOrigem = 0;
		pilhaDestino = 0;
		quantidade = 0;
	}
}
