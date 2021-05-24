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

    public ImagemPanel(BufferedImage imagem) {
       setImage(imagem);
       this.repaint();
    }
    
    public void setImage(BufferedImage imagem) {
    	this.image = imagem;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}