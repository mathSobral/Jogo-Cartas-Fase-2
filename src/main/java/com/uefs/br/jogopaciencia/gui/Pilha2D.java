package com.uefs.br.jogopaciencia.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.uefs.br.jogopaciencia.models.NoCarta;
import com.uefs.br.jogopaciencia.models.Pilha;

public class Pilha2D extends JPanel {
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
	private Selecao faixaSelecao;
	private SelecaoEvento eventoSelecao;


	/**
	 * 
	 * @param x
	 * @param y
	 * @param espacamentoHorizontal
	 * @param espacamentoVertical
	 * @param sprite
	 */
	public Pilha2D(int x, int y, int espacamentoHorizontal, int espacamentoVertical, Sprite sprite) {
		this.espacamentoHorizontal = espacamentoHorizontal;
		this.espacamentoVertical = espacamentoVertical;
		this.x = x;
		this.y = y;
		this.spriteCartas = sprite;
		this.setBounds(x, y, 55, getAlturaDoPainel());
		setLayout(null);
		paineisDeCartas = new ArrayList<>();
		faixaSelecao = new Selecao();
		setOpaque(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(paineisDeCartas.isEmpty())
					eventoSelecao.selecaoFeita(faixaSelecao, pilha);
			}
		});

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


	public void atualizarExibicao() {
		//TODO quando tiver 0 e 0 de offset, só renderizar a ultima carta
		this.setBounds(x, y, getLarguraDoPainel(), getAlturaDoPainel());

		for(ImagemPanel painel : paineisDeCartas) 
			this.remove(painel);


		paineisDeCartas = new ArrayList<>(); //TODO nao instanciar, reaproveitar os panel que ja tao la

		int x = 0;
		int y = 0;
		int posicalDaCarta = 0;

		for(NoCarta carta : pilha.getCartas()) {
			ImagemPanel painelCarta = new ImagemPanel(spriteCartas.getImage(carta.getNaipeInteiro(), carta.getNumero() - 1), 
													  spriteCartas.getImageSelecionado(carta.getNaipeInteiro(), carta.getNumero() - 1));
			
			boolean eUltima = (posicalDaCarta++ == (pilha.tamanho() - 1));
			
			painelCarta.setEstaSelecionado(false);
			
			painelCarta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					cliqueCarta(painelCarta, carta);
				}
			});

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
	
	private void cliqueCarta(ImagemPanel painelCarta, NoCarta carta){
		faixaSelecao.adicionarIndice(pilha.getCartas().indexOf(carta));
		
		if(eventoSelecao != null)
			eventoSelecao.selecaoFeita(faixaSelecao, pilha);
		
		if(faixaSelecao.possuiSelecao()) {
			atualizarSelecaoPilha(faixaSelecao, true);
		}
		else
			atualizarSelecaoPilha(faixaSelecao, false);
	}
	
	public void resetarExibicao() {
		faixaSelecao.resetar();
		atualizarSelecaoPilha(faixaSelecao, false);
	}
	
	private void atualizarSelecaoPilha(Selecao faixa, boolean valor) {
		int inicio = 0;
		int fim = paineisDeCartas.size() - 1;
		
		if(faixaSelecao.possuiSelecao()) {
			inicio = faixaSelecao.getInicio();
			
			if(faixaSelecao.possuiFaixaSelecao()){
				fim = paineisDeCartas.size() - 1;
			}
			else
				fim = inicio;
		}

		for(int i = 0; i <= paineisDeCartas.size() - 1; i++) {
			paineisDeCartas.get(i).setEstaSelecionado(valor && (i >= inicio && i <=fim));
		}
	}

	public void setEventoSelecao(SelecaoEvento eventoSelecao) {
		this.eventoSelecao = eventoSelecao;
	}
	
}
