package dungeonmeshigame;

import java.util.Scanner;
import java.util.ArrayList;

public class ScorpionGlandCard extends Card {
    private int damage;

    public ScorpionGlandCard(String name, int damage, int cost){
        super(name, cost);
        this.damage = damage;
    }

public boolean useCard(BattleState battle, ArrayList<Character> targets){
    if(battle.party.energy < this.getCost()){
        this.setUseLog("Você não tem energia para usar [" + this.getName() + "]");
        return false;
    }
    Enemy target = (Enemy) targets.get(0);
    target.takeDamage(damage);
    PoisonEffect poison = new PoisonEffect(target, damage, damage);
    target.addEffect(battle.publisher, poison);
    battle.hand.remove(this);
    battle.deck.discard_pile.add(this);
    battle.party.energy -= this.getCost();
    this.setUseLog("Usou [" + this.getName() + "]: " + target.name + " tomou " + this.damage + " de dano.");
    return true;
    }

    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("|Causa " + this.damage + " de dano");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }

    public void printUseLog(){
        System.out.println(this.getUseLog());
    }

    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        System.out.println("Escolha uma alvo:");
        for(int i = 0; i < battle.enemies.size(); i++){
            System.out.println("(" + (i+1) + ") " + battle.enemies.get(i).name);
        }
        int choice = scan.nextInt();
        if (choice >= 1 && choice <= battle.enemies.size()){
            ArrayList<Character> return_list = new ArrayList<Character>();
            return_list.add(battle.enemies.get(choice-1));
            return return_list;
        } else {
            System.out.println("Escolha inválida!");
            return this.askForTarget(battle, scan);
        }
    }
}