package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Representa uma carta de ataque que inflige Dano base e aplica um estado de Veneno.
 */
public class PoisonStingCard extends SwordCard{
    int poison_power;
    
    public PoisonStingCard(String name, int power, int damage, int cost){
        super(name, damage, cost);
        this.poison_power = power;
   }

   public PoisonStingCard(PoisonStingCard cloned){
        super(cloned.getName(), cloned.damage, cloned.getCost());
        this.poison_power = cloned.poison_power;
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
        PoisonEffect psn_effect = new PoisonEffect("Veneno", target.get(0), this.poison_power);
        target.get(0).addEffect(battle.publisher, psn_effect);
        target.get(0).takeDamage(this.damage + getDamageModifiers(battle));
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " tomou " + (this.damage + getDamageModifiers(battle) + " de dano, e foi afligido com " + psn_effect.getString()));
        return true;
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
}