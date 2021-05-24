package com.uefs.br.jogopaciencia.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.uefs.br.jogopaciencia.interfaces.ObserverJogada;
import com.uefs.br.jogopaciencia.models.NoCarta;
import com.uefs.br.jogopaciencia.models.Pilha;

public class PilhaGUI extends JPanel implements ObserverJogada{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pilha pilha;
	private int espacamentoHorizontal;
	private int espacamentoVertical;
	private ArrayList<ImagemPanel> paineisDeCartas;
	private int x;
	private int y;
	private Sprite spriteCartas;


	/**
	 * 
	 * @param x
	 * @param y
	 * @param espacamentoHorizontal
	 * @param espacamentoVertical
	 * @param sprite
	 */
	public PilhaGUI(int x, int y, int espacamentoHorizontal, int espacamentoVertical, Sprite sprite) {
		this.espacamentoHorizontal = espacamentoHorizontal;
		this.espacamentoVertical = espacamentoVertical;
		this.x = x;
		this.y = y;
		this.spriteCartas = sprite;
		this.setBounds(x, y, 55, getAlturaDoPainel());
		setLayout(null);
		paineisDeCartas = new ArrayList<>();
		this.setOpaque(false);
	}

	private int getAlturaDoPainel() {
		if(pilha == null || pilha.tamanho() == 0) {
			return Constantes.ALTURA_CARTA;
		}
		int alturaDoPainel = (espacamentoVertical * (pilha.tamanho() - 1)) + Constantes.ALTURA_CARTA;
		return  alturaDoPainel;
	}



	private int getLarguraDoPainel() {
		if(pilha == null || pilha.tamanho() == 0 || espacamentoHorizontal == 0) {
			return Constantes.LARGURA_CARTA;
		}
		int larguraDoPainel = ((espacamentoHorizontal + Constantes.LARGURA_CARTA) * (pilha.tamanho() - 1));
		return  larguraDoPainel;
	}

	public void setPilha(Pilha pilha) {
		this.pilha = pilha;
		atualizarExibicao();
	}

	@Override
	public void notificarJogadaRealizada() {
		atualizarExibicao();
	}

	public void atualizarExibicao() {
		//TODO quando tiver 0 e 0 de offset, só renderizar a ultima carta
		this.setBounds(x, y, getLarguraDoPainel(), getAlturaDoPainel());

		for(ImagemPanel painel : paineisDeCartas) 
			this.remove(painel);


		paineisDeCartas = new ArrayList<>();

		int x = 0;
		int y = 0;
		int indicePilha = 0;

		for(NoCarta carta : pilha.getCartas()) {
			ImagemPanel painelCarta = new ImagemPanel(spriteCartas.getImage(carta.getNaipeInteiro(), carta.getNumero() - 1));

			boolean eUltima = (indicePilha++ == (pilha.tamanho() - 1));

			if(espacamentoHorizontal == 0)
				painelCarta.setBounds(x, y, Constantes.LARGURA_CARTA, eUltima ? (Constantes.ALTURA_CARTA) : espacamentoVertical);
			else
				painelCarta.setBounds(x, y, Constantes.LARGURA_CARTA, Constantes.ALTURA_CARTA);

			this.add(painelCarta);
			this.paineisDeCartas.add(painelCarta);

			x = espacamentoHorizontal > 0 ? (x + espacamentoHorizontal + Constantes.LARGURA_CARTA) : x;
			y = espacamentoVertical > 0 ? (y + espacamentoVertical) : y;
		}

		this.repaint();
	}

}
