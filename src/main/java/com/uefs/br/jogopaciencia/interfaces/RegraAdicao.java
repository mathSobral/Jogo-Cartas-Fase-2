package com.uefs.br.jogopaciencia.interfaces;

import com.uefs.br.jogopaciencia.models.NoCarta;

public interface RegraAdicao {
	public boolean permitir(NoCarta cartaAnterior, NoCarta novaCarta);
}
