package com.uefs.br.jogopaciencia.models;


final public class Carta {
	private int numero;
	private String naipe;
	private boolean viradaParaBaixo = false;


	public Carta(int numero, String naipe) {
		this.numero = numero;
		this.naipe = naipe;

	}
	public Carta(){

	}

	public int getNumero() {
		return numero;
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
	
	public String getCor() {
		if(naipe.equals("COPAS") || naipe.equals("OURO"))
			return "VERMELHO";
		
		return "PRETO";
	}

	public String getNaipe() {
		return naipe;
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

	public int getNaipeInteiro() {
 		switch(naipe) {
		case "COPAS":
			return 1;
		case "ESPADAS":
			return 0;
		case "OURO":
			return 3;
		case "PAUS":
			return 2;
		}
		return -1;
	}

	/**
	 * Mostra uma representação do objeto da carta no padrão: "A de COPAS"
	 * @return String
	 */
	@Override
	public String toString() {
		return "[" + this.getNumRep() +" "+ this.getNaipe() + "] ";
	}

}