package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Representa uma carta de ataque que inflige Dano base e aplica um estado de Veneno.
 */
public class PoisonCard extends Card{
    int poison_power;
    int damage;
    
    public PoisonCard(String name, int power, int damage, int cost){
        super(name, cost);
        this.poison_power = power;
        this.damage = damage;
   }

    /**
     * Usa a carta gastando energia do grupo, causa dano e aplica o efeito 
     * de veneno no inimigo alvo. Envia a carta para o descarte em caso de sucesso.
     * * @param battle O estado da batalha atual.
     * @param target O alvo ou lista de alvos (neste caso, usa apenas o primeiro índice).
     * @return true se foi possível jogar, false caso falte energia.
     */
    public boolean useCard(BattleState battle, ArrayList<Character> target){
        if(battle.party.energy < this.getCost()){
            this.setUseLog("Você não tem energia para usar [" + this.getName() + "]");
            return false;
        }
        PoisonEffect psn_effect = new PoisonEffect(target.get(0), this.poison_power ,this.poison_power);
        target.get(0).addEffect(battle.publisher, psn_effect);
        target.get(0).takeDamage(this.damage + getDamageModifiers(battle));
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " tomou " + (this.damage + getDamageModifiers(battle) + " de dano, e foi afligido com " + psn_effect.getString()));
        return true;
    }

    /**
     * Calcula eventuais modificadores de dano causados por efeitos de aumento de Força.
     * * @param battle O estado da batalha (para identificar quem tem o turno atual).
     * @return O valor extra a adicionar ao dano base da carta.
     */
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
        System.out.println("|Aflige o alvo com Veneno (" + this.poison_power + ")");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }

    /**
     * Pede ao jogador via terminal que selecione o alvo inimigo onde pretende usar o ataque.
     */
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
        System.out.println("Escolha um alvo:");
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