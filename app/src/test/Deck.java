import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public ArrayDeque<Card> cards;
    public ArrayList<Card> discard_pile;

    public Deck(){
        cards = new ArrayDeque<Card>();
        discard_pile = new ArrayList<Card>();
    }

    public void shuffleDeck(){
        ArrayList<Card> cardsList = new ArrayList<Card>(this.cards);
        Collections.shuffle(cardsList);
        this.cards = new ArrayDeque<Card>(cardsList);
    }

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


