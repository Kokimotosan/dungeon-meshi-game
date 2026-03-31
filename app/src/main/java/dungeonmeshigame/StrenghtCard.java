package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Scanner;

public class StrenghtCard extends Card{
    int power;
    int duration;
    
    public StrenghtCard(String name, int power, int duration, int cost){
        super(name, cost);
        this.power = power;
        this.duration = duration;
    }

    public boolean useCard(BattleState battle, ArrayList<Character> target){
        if(battle.party.energy < this.getCost()){
            this.setUseLog("Você não tem energia para usar [" + this.getName() + "]");
            return false;
        }
        StrenghtEffect str_effect = new StrenghtEffect(target.get(0), this.power, this.duration);
        target.get(0).addEffect(battle.publisher, str_effect);
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " foi afligido com " + str_effect.getString());
        return true;
    }

    public void printUseLog(){
        System.out.println(this.getUseLog());
    }

    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("|Aflige um aliado com Força (" + this.power + ") por " + this.duration + " rodada(s).");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }

    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        System.out.println("Escolha uma alvo:");
        for(int i = 0; i < battle.party.members.size(); i++){
            System.out.println("(" + (i+1) + ") " + battle.party.members.get(i).name);
        }
        int choice = scan.nextInt();
        if (choice >= 1 && choice <= battle.party.members.size()){
            ArrayList<Character> return_list = new ArrayList<Character>();
            return_list.add(battle.party.members.get(choice-1));
            return return_list;
        } else {
            System.out.println("Escolha inválida!");
            return this.askForTarget(battle, scan);
        }
    }    
}