package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Representa uma carta vazia (Nula).
 * É frequentemente utilizada como variável auxiliar quando o jogador ainda não tomou
 * uma ação válida ou precisa descartar opções nulas, evitando erros de apontadores nulos no código.
 */
public class EmptyCard extends Card {

    /**
     * Construtor da carta vazia, com custo 0.
     */
    public EmptyCard() {
        super("EmptyCard", 0);
    }
    
    public void printCard(){
        return;
    }

    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        return new ArrayList<Character>();
    }

    /**
     * Uma carta vazia nunca possui efeitos ao ser usada, logo devolve sempre falso.
     */
    public boolean useCard(BattleState battle, ArrayList<Character> target){
        return false;
    }

    public void printUseLog(){
        return;
    }
}