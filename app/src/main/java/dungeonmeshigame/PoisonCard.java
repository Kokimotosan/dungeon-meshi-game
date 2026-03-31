package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Scanner;

public class PoisonCard extends Card{
    int power;
    int damage;
    
    public PoisonCard(String name, int power, int damage, int cost){
        super(name, cost);
        this.power = power;
        this.damage = damage;
   }

    public boolean useCard(BattleState battle, ArrayList<Character> target){
        if(battle.party.energy < this.getCost()){
            this.setUseLog("Você não tem energia para usar [" + this.getName() + "]");
            return false;
        }
        PoisonEffect psn_effect = new PoisonEffect(target.get(0), this.power ,this.power);
        target.get(0).addEffect(battle.publisher, psn_effect);
        target.get(0).takeDamage(this.damage + getDamageModifiers(battle));
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " tomou " + (this.damage + getDamageModifiers(battle) + " de dano, e foi afligido com " + psn_effect.getString()));
        return true;
    }

    public int getDamageModifiers(BattleState battle){
        int mod = 0;
        Character current = battle.getTurnCharacter();
        for(int i = 0; i < current.effects.size(); i++){
            if(current.effects.get(i) instanceof StrenghtEffect eff){
                mod += eff.getPower();
            }
        }
        return mod;
    }

    public void printUseLog(){
        System.out.println(this.getUseLog());
    }

    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("|Causa " + this.damage + " de dano a um inimigo.");
        System.out.println("|Aflige o alvo com Veneno (" + this.power + ")");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }

    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        ArrayList<Character> aux = new ArrayList<Character>();
        for(int i = 0; i < battle.enemies.size(); i++){
            if(battle.enemies.get(i).isAlive()){
                aux.add(battle.enemies.get(i));
            }
        }
        if(aux.size() == 0){
            System.out.println("Não há nenhum alvo válido.");
            return aux;
        }
        System.out.println("Escolha uma alvo:");
        for(int i = 0; i < aux.size(); i++){
            System.out.println("(" + (i+1) + ") " + aux.get(i).name);
        }
        int choice = scan.nextInt();
        if (choice >= 1 && choice <= aux.size()){
            ArrayList<Character> return_list = new ArrayList<Character>();
            return_list.add(aux.get(choice-1));
            return return_list;
        } else {
            System.out.println("Escolha inválida!");
            return this.askForTarget(battle, scan);
        }
    }    
}