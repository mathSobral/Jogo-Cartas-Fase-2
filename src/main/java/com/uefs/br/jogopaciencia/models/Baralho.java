package com.uefs.br.jogopaciencia.models;

import java.util.Stack;
import com.uefs.br.jogopaciencia.main.MersenneTwisterFast;

/**
 * Baralho de cartas, possui todas as 52 cartas e objeto de carta virada.
 * @author Willy G. M. Barro Raffel
 */
public class Baralho {
    public Stack<NoCarta> cartas;
    private boolean isDuplo = false;

    public static enum ENaipe {
        COPAS, OURO, ESPADAS, PAUS
    }
    
    public static enum ECor {
        VERMELHO, PRETO
    }
    
    final public static Naipe[] naipes = {
        new Naipe(ENaipe.COPAS, ECor.VERMELHO),
        new Naipe(ENaipe.OURO, ECor.VERMELHO),
        new Naipe(ENaipe.ESPADAS, ECor.PRETO),
        new Naipe(ENaipe.PAUS, ECor.PRETO)
    };
    
    // Carrega o icone da carta fechada
    //public final static ImageIcon cartaFechada = new ImageIcon(ClassLoader.getSystemResource("paciencia/resources/cartas/back.png"));
    
    public Baralho() {
    	criarCartas();
 
        // Embaralha
        this.embaralhar();
    }
    
    public Baralho(boolean isDuplo) {
    	criarCartas();
 
    	this.isDuplo = isDuplo;
        // Embaralha
        this.embaralhar();
    }
    
    private void criarCartas() {
    	cartas = new Stack<NoCarta>();
    	
    	// Cria as cartas
        for(int numCarta = 0; numCarta < 13; numCarta++) {
            for(Naipe naipe : Baralho.naipes) {
                NoCarta nc = new NoCarta(numCarta + 1, naipe);
                cartas.push(nc);
            }
        }
    }
    
    public void embaralhar() {
    	if(cartas.isEmpty())
    		criarCartas();
    	
        MersenneTwisterFast r = new MersenneTwisterFast();
        for(int i = 0; i < 99999; i++) {
            int rand1 = r.nextInt(cartas.size());
            int rand2 = r.nextInt(cartas.size());
            
            NoCarta aux = cartas.get(rand1);
            cartas.set(rand1, cartas.get(rand2));
            cartas.set(rand2, aux);
        }
    }
    
    public NoCarta retiraCartaTopo() {
        if(cartas.isEmpty()) {
        	if(isDuplo) {
        		embaralhar();
        		isDuplo = false;
        	}
        	else
        		return null;
        }
        
        return cartas.pop();
    }
}
