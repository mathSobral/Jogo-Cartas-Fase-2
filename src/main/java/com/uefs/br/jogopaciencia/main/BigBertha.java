package com.uefs.br.jogopaciencia.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uefs.br.jogopaciencia.gui.Constantes;
import com.uefs.br.jogopaciencia.gui.ImagemPanel;
import com.uefs.br.jogopaciencia.gui.PilhaGUI;
import com.uefs.br.jogopaciencia.gui.Sprite;
import com.uefs.br.jogopaciencia.models.JogoStrategy;
import com.uefs.br.jogopaciencia.models.PacienciaBigBertha;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BigBertha extends JFrame {

	private ImagemPanel contentPane;
	private Sprite sprite;
	private JogoStrategy jogo;
	private PilhaGUI[] pilhasGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BigBertha frame = new BigBertha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public BigBertha() throws Exception {
		sprite = new Sprite();
		jogo = new PacienciaBigBertha();
		sprite.carregarImagem("./resources/sprites.png");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 688);
		contentPane = new ImagemPanel( ImageIO.read(new File("./resources/background.png")));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNovoJogo = new JButton("Novo Jogo");
		btnNovoJogo.addActionListener((ActionEvent arg0) -> {try {
			novoJogo();
		} catch (Exception e) {
			System.out.println("Nao foi possivel iniciar um novo jogo.");
		}});
		btnNovoJogo.setBounds(826, 588, 124, 23);
		contentPane.add(btnNovoJogo);
		
		pilhasGUI = new PilhaGUI[25];
		
		
		jogo.novoJogo();
		

		instanciarEstoque(jogo);
		instanciarFundacoes(jogo);
		instanciarFileiras(jogo);

	}
	
	private void novoJogo() throws Exception {
		jogo.novoJogo();
		int numeroPilha = PacienciaBigBertha.ESTOQUE;
		for(PilhaGUI pilha2d : pilhasGUI) {
			pilha2d.setPilha(jogo.getPilha(numeroPilha++));
		}
	}
	
	private void instanciarEstoque(JogoStrategy jogo) throws Exception {
		PilhaGUI panel = new PilhaGUI(73, 470, 5, 0, sprite);
		panel.setPilha(jogo.getPilha(PacienciaBigBertha.ESTOQUE));
		contentPane.add(panel);
		
		pilhasGUI[0] = panel;
	}
	
	private void instanciarFundacoes(JogoStrategy jogo) throws Exception {
		int x = 223;
		int cursor = 1;
		for(int i = PacienciaBigBertha.FUNDACAO_1; i <= PacienciaBigBertha.FUNDACAO_9_REIS; i++) {
			PilhaGUI panel = new PilhaGUI(x, 50, 0, 0, sprite);
			panel.setPilha(jogo.getPilha(i));
			contentPane.add(panel);
			x = x + Constantes.LARGURA_CARTA + 5;
			pilhasGUI[cursor++] = panel;
		}
	}
	
	private void instanciarFileiras(JogoStrategy jogo) throws Exception {
		int x = 43;
		int cursor = 10;
		for(int i = PacienciaBigBertha.FILEIRA_1; i <= PacienciaBigBertha.FILEIRA_14; i++) {
			PilhaGUI panel = new PilhaGUI(x, 245, 0, 25, sprite);
			panel.setPilha(jogo.getPilha(i));
			contentPane.add(panel);
			x = x + Constantes.LARGURA_CARTA + 5;
			pilhasGUI[cursor++] = panel;
		}
	}
}
