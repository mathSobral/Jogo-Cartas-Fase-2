package com.uefs.br.jogopaciencia.main;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.uefs.br.jogopaciencia.gui.Constantes;
import com.uefs.br.jogopaciencia.gui.ImagemPanel;
import com.uefs.br.jogopaciencia.gui.Movimento;
import com.uefs.br.jogopaciencia.gui.Pilha2D;
import com.uefs.br.jogopaciencia.gui.Selecao;
import com.uefs.br.jogopaciencia.gui.Sprite;
import com.uefs.br.jogopaciencia.gui.SelecaoEvento;
import com.uefs.br.jogopaciencia.models.JogoStrategy;
import com.uefs.br.jogopaciencia.models.PacienciaBigBertha;
import com.uefs.br.jogopaciencia.models.Pilha;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;

public class BigBertha extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4385634279358081846L;
	private ImagemPanel contentPane;
	private Sprite sprite;
	private JogoStrategy jogo;
	private Pilha2D[] pilhasGUI;
	private Movimento proximoMovimento;
	private JLabel lblMensagem;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public BigBertha() throws Exception {
		sprite = new Sprite();
		jogo = new PacienciaBigBertha();
		proximoMovimento = new Movimento();
		sprite.carregarImagem("./resources/sprites.png");
		sprite.carregarImagemSelecionado("./resources/sprites_selected.png");

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

		lblMensagem = new JLabel("");
		lblMensagem.setForeground(Color.WHITE);
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagem.setBounds(258, 592, 445, 14);
		contentPane.add(lblMensagem);

		pilhasGUI = new Pilha2D[25];


		jogo.novoJogo();


		instanciarEstoque(jogo);
		instanciarFundacoes(jogo);
		instanciarFileiras(jogo);

	}

	private void novoJogo() throws Exception {
		jogo.novoJogo();
		int numeroPilha = PacienciaBigBertha.ESTOQUE;
		for(Pilha2D pilha2d : pilhasGUI) {
			pilha2d.setPilha(jogo.getPilha(numeroPilha++));
		}
	}

	private void instanciarEstoque(JogoStrategy jogo) throws Exception {
		Pilha2D panel = new Pilha2D(14, 50, 0, 25, sprite);
		panel.setPilha(jogo.getPilha(PacienciaBigBertha.ESTOQUE));
		contentPane.add(panel);
		adicionarEventoSelecao(panel);

		pilhasGUI[0] = panel;
	}

	private void instanciarFundacoes(JogoStrategy jogo) throws Exception {
		int x = 257;
		int cursor = 1;
		for(int i = PacienciaBigBertha.FUNDACAO_1; i <= PacienciaBigBertha.FUNDACAO_9_REIS; i++) {
			Pilha2D panel = new Pilha2D(x, 50, 0, 0, sprite);
			panel.setPilha(jogo.getPilha(i));
			contentPane.add(panel);
			x = x + Constantes.LARGURA_CARTA + 5;
			pilhasGUI[cursor++] = panel;
			adicionarEventoSelecao(panel);
		}
	}

	private void instanciarFileiras(JogoStrategy jogo) throws Exception {
		int x = 79;
		int cursor = 10;
		for(int i = PacienciaBigBertha.FILEIRA_1; i <= PacienciaBigBertha.FILEIRA_14; i++) {
			Pilha2D panel = new Pilha2D(x, 145, 0, 25, sprite);
			panel.setPilha(jogo.getPilha(i));
			contentPane.add(panel);
			x = x + Constantes.LARGURA_CARTA + 5;
			pilhasGUI[cursor++] = panel;
			adicionarEventoSelecao(panel);
		}
	}

	private void adicionarEventoSelecao(Pilha2D pilha2d) {
		pilha2d.setEventoSelecao(new SelecaoEvento() {
			@Override
			public void selecaoFeita(Selecao selecao, Pilha pilha) {
				proximoMovimento.adicionarPilha(pilha.getNumero(), selecao.getNumeroDeItensSelecionados());
				
				if(proximoMovimento.movimentoFormado()) {
					try {
						int pilhaOrigem = proximoMovimento.getPilhaOrigem();
						int quantidade = proximoMovimento.getQuantidade();
						int pilhaDestino = proximoMovimento.getPilhaDestino();

						proximoMovimento.resetar();
						
						pilhasGUI[pilhaOrigem - 1].resetarExibicao();
						pilhasGUI[pilhaDestino - 1].resetarExibicao();


						if(quantidade > 1)
							jogo.moverCarta(pilhaOrigem, quantidade, pilhaDestino);
						else{
							jogo.moverCarta(pilhaOrigem, pilhaDestino);

						}

						
						pilhasGUI[pilhaOrigem - 1].atualizarExibicao();;
						pilhasGUI[pilhaDestino - 1].atualizarExibicao();
						verificaFimJogo();



					} 
					catch (Exception e) {
						exibirMensagem(e.getMessage(),false);
					}
				}
			}

		});
	}

	private void verificaFimJogo(){
		int soma = 0;
		for(Pilha2D pilha2d : pilhasGUI){
			if(pilha2d.getPilha().getNumero() > 1 &&  pilha2d.getPilha().getNumero() < 11){
				soma+= pilha2d.getPilha().getCartas().size();
			}
		}
		if(soma == 104){
			exibirMensagem("Parabéns, você ganhou o jogo!",true);
		}

	}

	private void exibirMensagem(String mensagem, boolean fim) {
		lblMensagem.setText(mensagem);
		lblMensagem.setVisible(true);
		if(! fim){
			int delay = 3000; //milliseconds
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					lblMensagem.setVisible(false);
				}
			};
			new javax.swing.Timer(delay, taskPerformer).start();
		}
		else 
			JOptionPane.showMessageDialog(null,mensagem);  

	}
}
