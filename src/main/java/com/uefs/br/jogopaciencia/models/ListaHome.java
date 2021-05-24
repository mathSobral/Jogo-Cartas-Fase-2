package com.uefs.br.jogopaciencia.models;


import com.uefs.br.jogopaciencia.interfaces.IHome;
import com.uefs.br.jogopaciencia.facade.PacienciaFacade;



/**
 * Classe de lista de cartas.
 * 7 instancias dessa classe são criadas para o jogo funcionar.
 * Elas são a área de trabalho principal do paciência.
 * 
 * @author Willy G. M. Barro Raffel
 */
public class ListaHome extends ListaCarta implements IHome {
   
    public ListaHome(String nome) {
        super(nome);
    }
    
    
    /**
     * Insere uma carta na lista de cartas.
     * Este método deve ser usado apenas no inicio do jogo, para colocar
     * cartas aleatórias e sem validação na lista.
     * 
     * @param c 
     */
    public void inserir(NoCarta noCarta) {
        NoCarta antigoFinal = this.elementoFinal();
        this.inserirFinal(noCarta);
        
        // Vira as cartas (fecha a penultima, abre a ultima)
        
    }
    
    /**
     * Recebe um nó, valida e armazena na lista atual
     * 
     * @param carta No (sublista) de cartas a serem inseridas na lista.
     * @return boolean Se verdadeiro, inseriu com sucesso.
     */
    @Override
    public boolean receberNo(NoCarta carta)
        
    {
        
        
        // Tudo certo, agora sim inserimos na nova lista e removemos ele da lista antiga
        IHome listaFrom = (IHome) carta.getHome();
        listaFrom.remover(carta);
        this.inserirFinal(carta);
        carta.setHome(this);
        
        return true;
    }
    
    public void moverNo(int num, ListaHome lh)
    {
        NoCarta no = this.buscarNo(num);
        lh.receberNo(no);
    }
    
    /**
     * Valida um conjunto de nós.
     * Para um conjunto de nós ser válido e poder ser arrastado,
     * todas as cartas depois dele devem estar em ordem decrescente,
     * com cores intercaladas.
     */
    private boolean validaConjuntoNo(NoCarta no) {
        NoCarta aux = no;
        
        // Verifica se os nós estão na sequencia correta
        while(aux != null) {
            if(
                aux.getProx() != null &&
                !PacienciaFacade.cartaSequenciaValida(aux, aux.getProx())
            )
                return false;
            
            aux = aux.getProx();
        }
        
        return true;
    }
    
    @Override
    public void remover(NoCarta nc) {
        super.remover(nc);
        
    }
}