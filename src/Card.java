import java.util.ArrayList;

public abstract class Card {
    String name;
    int cost;

    public Card(String name, int cost){
        this.name = name;
        this.cost = cost;
    }
    public abstract boolean useCard(ArrayList<Character> target, Party party, Deck deck);
}
