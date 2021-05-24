/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uefs.br.jogopaciencia.facade;

import com.uefs.br.jogopaciencia.models.Baralho;
import com.uefs.br.jogopaciencia.models.ListaHome;
import com.uefs.br.jogopaciencia.models.MonteHome;
import com.uefs.br.jogopaciencia.models.Naipe;
import com.uefs.br.jogopaciencia.models.NoCarta;
import com.uefs.br.jogopaciencia.models.PilhaHome;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author matee
 */
public class PacienciaFacade {
    private Baralho baralho;
    public MonteHome descarte;
    private PilhaHome[] pilhas = new PilhaHome[4];
    private ListaHome[] tableaus = new ListaHome[7];
    private MonteHome monte;
    
    
    
    public String iniciarJogo(){
        baralho = new Baralho();
        for (int i=0; i <= 3; i++){
            pilhas[i] = new PilhaHome("Pilha "+i);
        }
        
        this.iniciarMonte();
        descarte = new MonteHome();
        this.preencherTableaus();
        return "Jogo inicializado!";
    }
   
    
     public void iniciarMonte() {
        // Separa os 8 montes de 3 cartas que não vão pras listas
        monte = new MonteHome();
        for(int j = 0; j < 24; j++) {
            NoCarta carta = this.baralho.retiraCartaTopo();
            monte.inserir(carta);
        }
    }
     
    private void preencherTableaus() {
        for (int i = 0; i < 7; i++) {
            ListaHome lista = new ListaHome("Lista " + (i + 1));

            // Retira do baralho e receberNo nas listas de cartas
            for (int j = 0; j < (i + 1); j++) {
                NoCarta nc = this.baralho.retiraCartaTopo();
                nc.setHome(lista);
                lista.inserir(nc);
            }

            this.tableaus[i] = lista;
        }
    }
    
    /**
     * Valida se a carta1 é a sequencia da carta2 (crescentemente).
     * 
     * @param carta1
     * @param carta2
     * @return 
     */
    public static boolean cartaSequenciaValida(NoCarta carta1, NoCarta carta2) {
        if (carta1 == null || carta2 == null)
            return false;

        return ((carta1.getNumero() == carta2.getNumero() - 1)
                && (carta1.getNaipe().getCor() != carta2.getNaipe().getCor()));
    }
    
    public ListaHome[] getListas() {
        return this.tableaus;
    }
    
    public MonteHome getMonteHome() {
        return this.monte;
    }
    
    public PilhaHome[] getBases() {
        return this.pilhas;
    }
    
    public Baralho getBaralho() {
        return this.baralho;
    }
    
    public void percorrerTableus(){
        for(int i = 0; i < 12; i++){
            
        }
    }
    
    /**
     * Verifica se o jogo acabou.O jogo termina quando o topo de todas as pilhas-base são K
     * @return
     */
    public boolean verificaFimDeJogo() {
        int numK = 0;
        for(PilhaHome ph : this.pilhas) {
            if(ph.elementoTopo() != null && ph.elementoTopo().getNumRep().equals("K")) {
                numK++;
            }
        }
        
        if(numK == 4) {
           return true;
        } else
        return false;
    }
    
    public void mostrarEstoque(){
        System.out.print("\n1 - ESTOQUE == ");
        
        for(int i = 0; i < monte.getMontes().size(); i++){
            Stack<NoCarta> cartas = monte.getMontes().get(i);
            for(int j = 0; j < cartas.size(); j++){
                System.out.print("[<>]");
                if(i!= (monte.getMontes().size()-1) || j!= (cartas.size()-1)){
                    System.out.print(", ");
                }
            }
        }
        System.out.println();
    }
    
    public void mostrarDescarte(){ 
        System.out.print("2 - DESCARTE == ");
        
        if(!descarte.getMontes().isEmpty()){
            Stack<NoCarta> cartas = descarte.getMontes().get(descarte.getMontes().size()-1);
            for(int j = 0; j < cartas.size(); j++){
                System.out.print(cartas.get(j).toString());
                if(j!= (cartas.size()-1)){
                    System.out.print(", ");
                }
            }
        }
    }
    
    public void mostrarDescarte(int opcao){
        if(opcao == 2){
            
            if(monte.getMontes().isEmpty()){
                monte = descarte;
                descarte = new MonteHome();
            }else{
                Stack<NoCarta> cartas = null;

                if(monte.getNumMonteAtivo() == 0){
                    cartas = monte.getMontes().get(0);
                    monte.getMontes().remove(0);
                    for(int i = 0; i < cartas.size(); i++){
                        descarte.inserir(cartas.get(i));
                    }
                    monte.setNumMonteAtivo();
                }else{
                    cartas = monte.retira3Cartas();
                    cartas = monte.getMontes().get(0);
                    monte.getMontes().remove(0);
                    for(int i = 0; i < cartas.size(); i++){
                        descarte.inserir(cartas.get(i));
                    }
                    monte.setNumMonteAtivo();
                }                 
            }
        }
    }
    
    public void mostrar(){
        
        for(int i = 0; i < monte.getMontes().size(); i++){
            Stack<NoCarta> cartas = monte.getMontes().get(i);
            for(int j = 0; j < cartas.size(); j++){
                System.out.print(cartas.get(j).toString());
                if(i!= (monte.getMontes().size()-1) || j!= (cartas.size()-1)){
                    System.out.print(", ");
                }
            }
        }
        System.out.println();
    }
    
    public void getLista() {
        
        for(int i=0; i<7; i++) {
            NoCarta carta = tableaus[i].elementoInicio();
            System.out.print(i+7 +" - TABLEAU "+ (i+1) +" == ");
            if(carta != null){
                while(carta.getProx() != null){
                    System.out.print("[<>], ");
                    carta = carta.getProx();
                }
                if(carta.getProx() == null){
                    System.out.print(carta.toString() + "\n");
                }
            }else{
                System.out.println();
            }
        }    
    }

    public void getFundacao() {
        System.out.println();
        for(int i=0; i<4; i++) {
            NoCarta carta = pilhas[i].elementoTopo();
            System.out.print(i+3 +" - FUNDACAO "+ (i+1) +" == ");
            if(carta != null){
                while(carta.getProx() != null){                
                    System.out.print("[<>], ");
                    carta = carta.getProx();
                }
                if(carta.getProx() == null){
                    System.out.print(carta.toString() + "\n");
                }             
            }else{
                System.out.println();
            }
        }       
    }
    
    public void moverCarta(int a, int b){
        
        if(b == 2){
            if(a == 1){
                this.mostrarDescarte(b);
            }
        }else if (a == 2){
            if(b > 2 && b < 7){
                Stack<NoCarta> cartas = descarte.getMontes().get(descarte.getMontes().size()-1);
                pilhas[b-3].receberNo(cartas.get(cartas.size()-1));
                if(descarte.getMontes().size() > 1){
                    descarte.getMontes().get(descarte.getMontes().size()-1).add(0, descarte.getMontes().get(descarte.getMontes().size()-2).pop());
                }
            }else if(b > 6 && b < 14){
                Stack<NoCarta> cartaA = descarte.getMontes().get(descarte.getMontes().size()-1);
                NoCarta cartaB = tableaus[b-7].elementoFinal();
                
                if(cartaSequenciaValida(cartaA.get(cartaA.size()-1), cartaB)){
                    tableaus[b-7].inserir(cartaA.get(cartaA.size()-1));
                    if(descarte.getMontes().size() > 1){
                      descarte.getMontes().get(descarte.getMontes().size()-1).add(0, descarte.getMontes().get(descarte.getMontes().size()-2).pop());  
                    }
                }
            }
        }else if (a > 6 && a < 14){
            if(b > 6 && b < 14){
                NoCarta cartaA = tableaus[a-7].elementoFinal();
                NoCarta cartaB = tableaus[b-7].elementoFinal();
                if(cartaSequenciaValida(cartaA, cartaB)){
                    tableaus[b-7].inserir(cartaA);
                    tableaus[a-7].remover(cartaA);
                }   
            }else if (b < 7 && b > 2){
                NoCarta cartaA = tableaus[a-7].elementoFinal();
                NoCarta cartaB = pilhas[b-3].elementoTopo();
                if(cartaA.getNumero()-1 == cartaB.getNumero() && cartaA.getNaipe() == cartaB.getNaipe()){
                    pilhas[b-3].receberNo(cartaA);
                    tableaus[a-7].remover(cartaA);
                }
            }
        }else if (a > 2 && a < 7){
            NoCarta cartaA = pilhas[a-3].elementoTopo();
            NoCarta cartaB = tableaus[b-7].elementoFinal();
            if(cartaSequenciaValida(cartaA, cartaB)){
                tableaus[b-7].receberNo(cartaA);
                pilhas[a-3].remover(cartaA);
            }
        }      
    }
}