package com.uefs.br.jogopaciencia.models;

import java.util.List;

public abstract class JogoStrategy {
	protected Pilha pilhas[];
	

	/**
	 * Metodo responsavel por instanciar as pilhas e definir os eventos que
	 * definem o funcionamento das pilhas
	 * @throws Exception
	 */
	public abstract void novoJogo() throws Exception;
	
	
	public String pedirDadosCarta(int numPilha) throws Exception {
		Pilha pilha = getPilha(numPilha);
		return pilha.toString();
	}
	
	public void mostrarJogo() throws Exception {
		for(int i = 0; i < pilhas.length; i++) {
			System.out.println(pedirDadosCarta(i+1));
		}
	}
	
	public void moverCarta(int numPilhaOrigem, int numPilhaDestino) throws Exception {
		if(numPilhaOrigem == numPilhaDestino)
			throw new Exception("Devem ser informados numeros de pilha diferentes!");
		
		Pilha pilhaOrigem = getPilha(numPilhaOrigem);
		Pilha pilhaDestino = getPilha(numPilhaDestino);
		if(numPilhaOrigem == 1){
			pilhaDestino.adicionarCarta(pilhaOrigem.getCartas().get(pilhaOrigem.getCartaSelecionada()));
			pilhaOrigem.removerCartaIndex(pilhaOrigem.getCartaSelecionada());
		}else{
			pilhaDestino.adicionarCarta(pilhaOrigem.espiar());
			pilhaOrigem.desempilhar();
		}
		

	}
	
	public void moverCarta(int numPilhaOrigem, int quantidade, int numPilhaDestino) throws Exception {
		if(numPilhaOrigem == numPilhaDestino)
			throw new Exception("Devem ser informados numeros de pilha diferentes!");
		
		Pilha pilhaOrigem = getPilha(numPilhaOrigem);
		Pilha pilhaDestino = getPilha(numPilhaDestino);
		
		List<NoCarta> subpilha = pilhaOrigem.getSubpilha(quantidade);
		int tamanhoSubpilha = subpilha.size();
		
		try {
			pilhaDestino.adicionarSubpilha(subpilha);
			
			for(int i = 0; i < tamanhoSubpilha; i++) 
				pilhaOrigem.desempilhar();
			
		}
		catch(Exception e) {
			throw e;
		}
		

	}
	
	public Pilha getPilha(int numPilha) throws Exception {
		int pilhaIndice = converterParaIndiceArray(numPilha);
		if(pilhas.length  < pilhaIndice)
			throw new Exception("Pilha informada e' invalida!");
		
		return pilhas[pilhaIndice];
	}
	
	protected abstract int converterParaIndiceArray(int indiceContante);
	
	public abstract void parabenizar();
	
	public void encerrarPrograma() {
		System.out.println("Jogo finalizado! Volte sempre");
		System.exit(0);
	}
}
