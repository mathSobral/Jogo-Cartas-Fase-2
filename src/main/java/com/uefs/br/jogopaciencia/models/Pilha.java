package com.uefs.br.jogopaciencia.models;

import java.util.List;
import java.util.Stack;

import com.uefs.br.jogopaciencia.interfaces.RegraAdicao;

public class Pilha {
	private Stack<Carta> cartas;
	private int numero;
	private RegraAdicao regraAdicao;
	private String nome;
	private int cartaSelecionada;

	public Pilha() {
		cartas = new Stack<Carta>();
	}
	

	public Pilha(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
		cartas = new Stack<Carta>();
	}

	public Pilha(int numero, RegraAdicao eventoAdicao) {
		this.numero = numero;
		this.regraAdicao = eventoAdicao;
		cartas = new Stack<Carta>();
	}

	/**
	 * Verifica se e possivel adicionar a carta, a partir do evento de adicao, e caso seja adiciona
	 * Caso nao seja possivel lanca uma excecao
	 * @param novaCarta
	 * @throws Exception
	 */
	public void adicionarCarta(Carta novaCarta) throws Exception {
		if(novaCarta == null)
			return;
		
		if(regraAdicao != null){
				Carta cartaAnterior =  cartas.isEmpty() ? null : cartas.peek();
				
				if(!regraAdicao.permitir(cartaAnterior, novaCarta))
					throw new Exception("Movimento inválido. Insercao não permitida!!!");
		}
		
		cartas.push(novaCarta);
	}

	public void adicionarCarta(int index, Carta carta)  {
		if(carta == null)
			return;
		cartas.add(index, carta);
	}

	public void removerCartaIndex(int index){
		cartas.remove(index);
	}

	public void adicionarCartaSemVerificacao(Carta carta){
		if(carta == null)
			return;
		cartas.push(carta);
	}

	public int tamanho() {
		return cartas.size();
	}
	
	public Carta removerCarta(int index) {
		return cartas.remove(index);
	}
	

	public Carta desempilhar() throws Exception {
		if(cartas.isEmpty())
			throw new Exception("Pilha "+ (numero + 1) + " vazia!");
		

		Carta cartaDesempilhada = cartas.pop();
		
		return cartaDesempilhada;
	}



	/**
	 * Método que oserva sem remover a carta do topo da pilha
	 * @return
	 * @throws Exception
	 */
	public Carta espiar() throws Exception {
		if(cartas.isEmpty())
			throw new Exception("Pilha "+ (numero + 1) + " vazia!");
		
		return cartas.peek();
	}

	public void virarCartas(int quantidade) {
		if(quantidade > cartas.size())
			quantidade = cartas.size();

		for(int i = 1; i <= quantidade; i++) {
			Carta carta = cartas.get(cartas.size() - i);
			if(carta.estaViradaParaBaixo())
				carta.virar();
		}
	}
	
	
	public Stack<Carta> getCartas(){
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

		for(Carta carta : cartas)
			cartasString += carta.toString();

		return cartasString;
	}


	public int quantidade() {
		return cartas.size();
	}


	public List<Carta> getSubpilha(int quantidade) {
		int fromIndex = cartas.size() - quantidade;
		int toIndex = cartas.size();
		return cartas.subList(fromIndex, toIndex);
	}

	public void setCartaSelecionada(int carta){
		this.cartaSelecionada = carta;
	}

	public int getCartaSelecionada(){
		return this.cartaSelecionada;
	}


	public void adicionarSubpilha(List<Carta> subpilha) throws Exception {
		if(subpilha.size() == 1) {
			adicionarCarta(subpilha.get(0));
			return;
		}
		
		Carta ultimaCarta = null;
		
		if(!cartas.isEmpty())
			ultimaCarta = cartas.peek();


		if(!regraAdicao.permitir(ultimaCarta, subpilha.get(0)))
			throw new Exception("Movimento inválido. Insercao não permitida!!!");
		
		int indiceDaCarta = 0;
		
		for(Carta carta: subpilha.subList(1, subpilha.size()))
				if(!regraAdicao.permitir(subpilha.get(indiceDaCarta++), carta))
					throw new Exception("Movimento inválido. Insercao não permitida!!!");
		
		cartas.addAll(subpilha);
		
	}

}
