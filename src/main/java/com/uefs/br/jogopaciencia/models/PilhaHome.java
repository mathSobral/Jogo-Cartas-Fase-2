 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uefs.br.jogopaciencia.models;

import com.uefs.br.jogopaciencia.interfaces.IHome;
import com.uefs.br.jogopaciencia.models.PilhaCarta;

/**
 *
 * @author willy
 */
public class PilhaHome extends PilhaCarta implements IHome {

    
    public PilhaHome(String nome) {
        super(nome);
        this.cartas = new NoCarta[13];
    }
    
    
   
    
    /**
     * Insere uma carta no topo da pilha de cartas.
     * Só podemos inserir a carta se ela for um A (se a pilha for vazia)
     * ou se ela for a proxima carta da sequencia.
     * 
     * @param carta
     * @return 
     */
    @Override
    public boolean receberNo(NoCarta carta) {
        
        NoCarta cartaTopo = this.elementoTopo();
        if((this.pilhaVazia() && carta.getNumero() == 1) ||
            (
                !this.pilhaVazia() &&
                cartaTopo.getNumero() == carta.getNumero() - 1 &&
                cartaTopo.getNaipe() == carta.getNaipe()
            )
        ) {
            IHome homeFrom = (IHome) carta.getHome();
            homeFrom.remover(carta);
            carta.setHome(this);
            return this.empilhar(carta);
        }
        return false;
    }    
        
    
    
    /**
     * Remove uma carta do topo do baralho (se disponível)
     * 
     * @return Retorna a ultima carta do baralho
     */
    public void remover(NoCarta nc) {
        this.desempilhar();
    }
    
    
}
