public class DamageCard extends Card {
    private int damage;

    public DamageCard(String name, int damage, int cost){
        super(name, cost);
        this.damage = damage;
    }

    public boolean useCard(Character target, Party party, Deck deck){
        if(party.energy < this.getCost()){
            return false;
        }
        target.takeDamage(damage);
        deck.discard_pile.add(this);
        party.energy -= this.getCost();
        return true;
    }

    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("| Causa " + this.damage + " de dano");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }
}
