import java.util.ArrayList;

public class DamageCard extends Card {
    private int damage;

    public DamageCard(String name, int damage, int cost){
        super(name, cost);
        this.damage = damage;
    }

    public boolean useCard(ArrayList<Character> target, Party party, Deck deck){
        if(party.energy < this.getCost()){
            return false;
        }
        target.get(0).takeDamage(damage);
        deck.discard_pile.add(this);
        return true;
    }

    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("| Causa " + this.damage + " de dano");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }
}
