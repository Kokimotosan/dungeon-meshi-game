public class ShieldCard extends Card {
    int shield;
    
    public ShieldCard(String name, int shield, int cost){
        super(name, cost);
        this.shield = shield;
    }

    public boolean useCard(Character target, Party party, Deck deck){
        if(party.energy < this.getCost()){
            return false;
        }
        target.gainShield(shield);
        deck.discard_pile.add(this);
        party.energy -= this.getCost();
        return true;
    }

    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println(this.getCost() + " custo de energia");
        System.out.println("Gera " + this.shield + " de escudo");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }
}
