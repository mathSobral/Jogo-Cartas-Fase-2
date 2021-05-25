package com.uefs.br.jogopaciencia.gui;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagemPanel extends JPanel{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private BufferedImage imageSelecionado;
	private boolean estaSelecionado;

    public ImagemPanel(BufferedImage imagem) {
       setImage(imagem);
       repaint();
    }
    
    public ImagemPanel(BufferedImage imagem, BufferedImage imageSelecionado) {
        setImage(imagem);
        setImageSelecionado(imageSelecionado);
        repaint();
     }
    
    public void setImage(BufferedImage imagem) {
    	this.image = imagem;
    	repaint();
    }
    
    public void setImageSelecionado(BufferedImage imageSelecionado) {
    	this.imageSelecionado = imageSelecionado;
    	repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(estaSelecionado ? imageSelecionado : image, 0, 0, this); // see javadoc for more info on the parameters            
    }
    
    public void setEstaSelecionado(boolean estaSelecionado) {
    	if(imageSelecionado == null)
    		return;
    	this.estaSelecionado = estaSelecionado;
    	repaint();
    }
    
    public void toogleEstaSelecionado() {
    	if(imageSelecionado == null)
    		return;
    	estaSelecionado = !estaSelecionado;
    	repaint();
    }
    
    public boolean estaSelecionado() {
    	return estaSelecionado;
    }

}