import java.util.ArrayList;

public class DamageCard extends Card {
    String name;
    int damage;
    int cost;

    public DamageCard(String name, int damage, int cost){
        super(name, cost);
        this.damage = damage;
    }

    public boolean useCard(ArrayList<Character> target, Party party, Deck deck){
        if(party.energy < this.cost){
            return false;
        }
        target.get(0).takeDamage(damage);
        deck.discard_pile.add(this);
        return true;
    }
}
