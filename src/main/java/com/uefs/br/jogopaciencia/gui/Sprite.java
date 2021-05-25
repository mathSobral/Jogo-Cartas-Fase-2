package com.uefs.br.jogopaciencia.gui;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage imagem;
	private BufferedImage imagemSelecionado;

	public Sprite() {
	}
	
	public void carregarImagem(String path) {
		try {
			imagem = ImageIO.read(new File(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(int row, int col) {
		int x = Constantes.LARGURA_CARTA * col;
		int y = Constantes.ALTURA_CARTA * row;
		
		return imagem.getSubimage(x, y, Constantes.LARGURA_CARTA, Constantes.ALTURA_CARTA);
	}
	
	public BufferedImage getImageSelecionado(int row, int col) {
		int x = Constantes.LARGURA_CARTA * col;
		int y = Constantes.ALTURA_CARTA * row;
		
		return imagemSelecionado.getSubimage(x, y, Constantes.LARGURA_CARTA, Constantes.ALTURA_CARTA);
	}

	public void carregarImagemSelecionado(String path) {
		try {
			imagemSelecionado = ImageIO.read(new File(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
