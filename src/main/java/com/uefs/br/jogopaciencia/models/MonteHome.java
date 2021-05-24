package com.uefs.br.jogopaciencia.models;

import com.uefs.br.jogopaciencia.interfaces.IHome;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Classe de monte de cartas.
 * Os montes possuem 3 cartas e são mostrados no topo esquerdo do jogo.
 * 
 * @author Willy G. M. Barro Raffel
 */
public class MonteHome implements IHome {
    private ArrayList<Stack<NoCarta>> montes = new ArrayList<Stack<NoCarta>>();

    @Override
    public boolean receberNo(NoCarta carta) {
        return false;
    }

    @Override
    public void remover(NoCarta nc) {
        int i = 0;
        Stack<NoCarta> monteRemover = null;
        for(Stack<NoCarta> monte : this.montes) {
            if(monte.contains(nc))
                monteRemover = monte;
            i++;
        }
        
        // Remove a carta do monte, se ele esvaziar, remove o monte do array original
        if(monteRemover != null) {
            monteRemover.remove(nc);
            
            if(monteRemover.size() > 0) {
                NoCarta carta = monteRemover.get(monteRemover.size()-1);
            } else {
                this.montes.remove(monteRemover);
            }
        }
    }
    
    Stack<NoCarta> monteAtual = null;
    public void inserir(NoCarta nc) {
        if(monteAtual == null || monteAtual.size() == 3) {
            monteAtual = new Stack<NoCarta>();
            this.montes.add(monteAtual);
        }
        
        nc.setHome(this);
        monteAtual.add(nc);
    }
    
    private int numMonteAtivo = 0;
    public Stack<NoCarta> retira3Cartas() {
        if(this.montes.isEmpty())
            return null;
        if(this.numMonteAtivo == 0){
            this.numMonteAtivo = 0; 
        }else if(this.numMonteAtivo < this.montes.size()){
            return this.montes.get(this.numMonteAtivo);
        }else{
            this.numMonteAtivo = 0;
        }
        return this.montes.get(this.numMonteAtivo);
    }
    
    public void setNumMonteAtivo(){
        this.numMonteAtivo++;
    }
    
    public int getNumMonteAtivo() {
        return this.numMonteAtivo;
    }
    
    public ArrayList<Stack<NoCarta>> getMontes() {
        return this.montes;
    }
    
    

}