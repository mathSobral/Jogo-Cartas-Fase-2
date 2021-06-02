package com.uefs.br.jogopaciencia.interfaces;

import com.uefs.br.jogopaciencia.models.Carta;

public interface RegraAdicao {
	public boolean permitir(Carta cartaAnterior, Carta novaCarta);
}
