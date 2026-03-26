import java.util.Scanner;
import java.util.ArrayList;

public class ShieldCard extends Card {
    int shield;
    
    public ShieldCard(String name, int shield, int cost){
        super(name, cost);
        this.shield = shield;
    }

    public boolean useCard(BattleState battle, ArrayList<Character> target){
        if(battle.party.energy < this.getCost()){
            this.setUseLog("Você não tem energia para usar [" + this.getName() + "]");
            return false;
        }
        target.get(0).gainShield(shield);
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " recebeu " + this.shield + " de escudo");
        return true;
    }

    public void printUseLog(){
        System.out.println(this.getUseLog());
    }

    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("|Gera " + this.shield + " de escudo para o usuário");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }

    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        ArrayList<Character> returnList = new ArrayList<Character>();
        returnList.add(battle.getTurnCharacter());
        return returnList;
    }
}
