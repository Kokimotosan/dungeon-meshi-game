import java.util.ArrayList;

public class ShieldCard extends Card {
    int shield;
    
    public ShieldCard(String name, int shield, int cost){
        super(name, cost);
        this.shield = shield;
    }

    public boolean useCard(ArrayList<Character> target, Party party, Deck deck){
        if(party.energy < this.getCost()){
            return false;
        }
        target.get(0).gainShield(shield);
        return true;
    }

    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println(this.getCost() + " custo de energia");
        System.out.println("Gera " + this.shield + " de escudo");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }
}
