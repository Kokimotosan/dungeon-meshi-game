import java.util.ArrayList;

public class ShieldCard extends Card {
    String name;
    int shield;
    int cost;
    
    public ShieldCard(String name, int shield, int cost){
        super(name, cost);
        this.shield = shield;
    }

    public boolean useCard(ArrayList<Character> target, Party party, Deck deck){
        if(party.energy < this.cost){
            return false;
        }
        target.get(0).gainShield(shield);
        return true;
    }
}
