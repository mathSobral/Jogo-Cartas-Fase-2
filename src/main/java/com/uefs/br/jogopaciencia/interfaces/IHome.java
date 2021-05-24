/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uefs.br.jogopaciencia.interfaces;


import com.uefs.br.jogopaciencia.models.NoCarta;

/**
 * Interface dos componentes "Home"
 * Todos eles devem poder receber uma carta
 * @author willy
 */
public interface IHome {
    public boolean receberNo(NoCarta carta);
    public void remover(NoCarta nc);
}
