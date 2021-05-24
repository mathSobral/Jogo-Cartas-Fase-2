package com.uefs.br.jogopaciencia.models;

import com.uefs.br.jogopaciencia.interfaces.IHome;
/**
 *
 * @author Mateus Guimarães e Karol Lima
 */
final public class NoCarta {
	private int numero;
	private Naipe naipe;

	private NoCarta prox = null;
	private boolean viradaParaBaixo = false;


	/**
	 * Instancia da classe pai (home), pode ser uma ListaHome ua PilhaHome ou Baralho
	 */
	private IHome home = null;

	public NoCarta(int numero, Naipe naipe) {
		this.numero = numero;
		this.naipe = naipe;

	}
	public NoCarta(){

	}

	public int getCountProx() {
		int totalProx = 0;
		NoCarta aux = this;
		while(aux.getProx() != null) {
			totalProx++;
			aux = aux.getProx();
		}

		return totalProx;
	}

	public NoCarta getProx() {
		return this.prox;
	}

	public void setProx(NoCarta prox) {
		this.prox = prox;
	}


	public int getNumero() {
		return this.numero;
	}

	/**
	 * Recupera a representação do número da carta, por ex:
	 * Ao invés de 13, 12, 11, 1 será retornado K, Q, J, A
	 * @return String
	 */
	public String getNumRep() {
		String ret;
		switch(this.numero) {
		case 1:
			ret = "A";
			break;
		case 11:
			ret = "J";
			break;
		case 12:
			ret = "Q";
			break;
		case 13:
			ret = "K";
			break;
		default: 
			ret = String.valueOf(this.numero);
			break;
		}

		return ret;
	}

	public String getNaipeRep() {
		String ret;
		switch(this.naipe.getNaipe()) {
		case COPAS:
			ret = "c";
			break;
		case ESPADAS:
			ret = "e";
			break;
		case OURO:
			ret = "o";
			break;
		case PAUS:
		default: 
			ret = "p";
			break;
		}

		return ret;
	}

	public Naipe getNaipe() {
		return this.naipe;
	}


	public boolean eAz() {
		return this.numero == 1;
	}

	public boolean eReis() {
		return this.numero == 13;
	}

	public boolean estaViradaParaBaixo() {
		return viradaParaBaixo;
	}

	public void virar() {
		viradaParaBaixo = !viradaParaBaixo;
	}

	/**
	 * Mostra uma representação do objeto da carta no padrão: "A de COPAS"
	 * @return String
	 */
	@Override
	public String toString() {
		return "[" + this.getNumRep() +" "+ this.getNaipe().getNaipe().name() + "] ";
	}

	/**
	 * Seta a home da carta (e também de todos as cartas filhas).
	 * Pode ser uma ListaHome, PilhaHome ou Baralho
	 * @param home 
	 */
	public void setHome(IHome home) {
		this.home = home;

		if(this.getProx() != null)
			this.getProx().setHome(home);
	}

	public IHome getHome() {
		return this.home;
	}
	public int getNaipeInteiro() {
		switch(this.naipe.getNaipe()) {
		case COPAS:
			return 1;
		case ESPADAS:
			return 0;
		case OURO:
			return 3;
		case PAUS:
		default: 
			return 2;
		}
	}
}