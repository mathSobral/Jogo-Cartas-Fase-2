package com.uefs.br.jogopaciencia.models;

import java.util.List;
import java.util.Stack;

import com.uefs.br.jogopaciencia.interfaces.RegraAdicao;

public class Pilha {
	private Stack<NoCarta> cartas;
	private int numero;
	private RegraAdicao regraAdicao;
	private String nome;

	public Pilha() {
		cartas = new Stack<NoCarta>();
	}
	

	public Pilha(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
		cartas = new Stack<NoCarta>();
	}

	public Pilha(int numero, RegraAdicao eventoAdicao) {
		this.numero = numero;
		this.regraAdicao = eventoAdicao;
		cartas = new Stack<NoCarta>();
	}

	/**
	 * Verifica se e possivel adicionar a carta, a partir do evento de adicao, e caso seja adiciona
	 * Caso nao seja possivel lanca uma excecao
	 * @param novaCarta
	 * @throws Exception
	 */
	public void adicionarCarta(NoCarta novaCarta) throws Exception {
		if(novaCarta == null)
			return;
		
		if(regraAdicao != null){
				NoCarta cartaAnterior =  cartas.isEmpty() ? null : cartas.peek();
				
				if(!regraAdicao.permitir(cartaAnterior, novaCarta))
					throw new Exception("Movimento inválido. Insercao não permitida!!!");
		}
		
		cartas.push(novaCarta);
	}

	public void adicionarCarta(int index, NoCarta carta)  {
		if(carta == null)
			return;
		cartas.add(index, carta);
	}

	public void adicionarCartaSemVerificacao(NoCarta carta){
		if(carta == null)
			return;
		cartas.push(carta);
	}

	public int tamanho() {
		return cartas.size();
	}
	
	public NoCarta removerCarta(int index) {
		return cartas.remove(index);
	}
	

	public NoCarta desempilhar() throws Exception {
		if(cartas.isEmpty())
			throw new Exception("Pilha "+ (numero + 1) + " vazia!");
		

		NoCarta cartaDesempilhada = cartas.pop();
		
		return cartaDesempilhada;
	}

	/**
	 * Método que oserva sem remover a carta do topo da pilha
	 * @return
	 * @throws Exception
	 */
	public NoCarta espiar() throws Exception {
		if(cartas.isEmpty())
			throw new Exception("Pilha "+ (numero + 1) + " vazia!");
		
		return cartas.peek();
	}

	public void virarCartas(int quantidade) {
		if(quantidade > cartas.size())
			quantidade = cartas.size();

		for(int i = 1; i <= quantidade; i++) {
			NoCarta carta = cartas.get(cartas.size() - i);
			if(carta.estaViradaParaBaixo())
				carta.virar();
		}
	}
	
	
	public Stack<NoCarta> getCartas(){
		return cartas;
	}

	
	public void setRegraAdicao(RegraAdicao evento) {
		this.regraAdicao = evento;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String toString() {
		String cartasString = nome + " - " + numero + " ";

		for(NoCarta carta : cartas)
			cartasString += carta.toString();

		return cartasString;
	}


	public int quantidade() {
		return cartas.size();
	}


	public List<NoCarta> getSubpilha(int quantidade) {
		int fromIndex = cartas.size() - quantidade;
		int toIndex = cartas.size();
		return cartas.subList(fromIndex, toIndex);
	}


	public void adicionarSubpilha(List<NoCarta> subpilha) throws Exception {
		if(subpilha.size() == 1) {
			adicionarCarta(subpilha.get(0));
			return;
		}
		
		if(!regraAdicao.permitir(cartas.peek(), subpilha.get(0)))
			throw new Exception("Movimento inválido. Insercao não permitida!!!");
		
		int indiceDaCarta = 0;
		
		for(NoCarta carta: subpilha.subList(1, subpilha.size()))
				if(!regraAdicao.permitir(subpilha.get(indiceDaCarta++), carta))
					throw new Exception("Movimento inválido. Insercao não permitida!!!");
		
		cartas.addAll(subpilha);
		
	}

}
