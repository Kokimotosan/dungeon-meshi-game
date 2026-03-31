package dungeonmeshigame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa o baralho de cartas do jogador durante a batalha.
 * <p>
 * Gere as cartas por comprar e a pilha de descarte, responsabilizando-se 
 * por baralhar o baralho quando as cartas se esgotam.
 * </p>
 */
public class Deck {
    public ArrayDeque<Card> cards;
    public ArrayList<Card> discard_pile;

    /**
     * Construtor que inicializa um baralho vazio e a respetiva pilha de descarte.
     */
    public Deck(){
        cards = new ArrayDeque<Card>();
        discard_pile = new ArrayList<Card>();
    }

    /**
     * Baralha todas as cartas que estão atualmente no baralho de compra.
     */
    public void shuffleDeck(){
        ArrayList<Card> cardsList = new ArrayList<Card>(this.cards);
        Collections.shuffle(cardsList);
        this.cards = new ArrayDeque<Card>(cardsList);
    }

    /**
     * Compra uma quantidade específica de cartas do baralho e adiciona-as à mão.
     * <p>
     * Se o baralho ficar sem cartas durante a compra, a pilha de descarte é baralhada
     * e transformada no novo baralho.
     * </p>
     * * @param hand A lista de cartas (mão do jogador) para onde as cartas irão.
     * @param ammount A quantidade de cartas a comprar.
     */
    public void draw(ArrayList<Card> hand, int ammount){
        for(int i = 0; i < ammount; i++){
            if(this.cards.isEmpty()){
                Collections.shuffle(this.discard_pile);
                this.cards = new ArrayDeque<Card>(discard_pile);
                this.discard_pile.clear();
            } else{
                hand.add(this.cards.removeFirst());
            }
        }
    }
}