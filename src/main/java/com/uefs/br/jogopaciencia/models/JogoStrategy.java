package com.uefs.br.jogopaciencia.models;

public abstract class JogoStrategy {
	protected Pilha pilhas[];
	

	/**
	 * Metodo responsavel por instanciar as pilhas e definir os eventos que
	 * definem o funcionamento das pilhas
	 * @throws Exception
	 */
	public abstract void novoJogo() throws Exception;
	
	public void virarCartas(int numeroCartas, int pilha) throws Exception{
		//TODO
	}

	
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
		
		pilhaDestino.adicionarCarta(pilhaOrigem.espiar());
		
		pilhaOrigem.desempilhar();
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
