package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa um inimigo específico: "Escorpião Gigante".
 * <p>
 * Este inimigo possui ataques físicos normais (pinçadas) e ataques que 
 * aplicam o efeito de veneno no jogador.
 * </p>
 */
public class HugeScorpion extends Enemy{
    public int next_attack;

    /**
     * Constrói um novo escorpião com valores predefinidos.
     * * @param index O índice do escorpião, para o distinguir de múltiplos escorpiões no campo de batalha.
     */
    public HugeScorpion(int index){
        super("Escorpião Gigante " + index, 12, 12, 0,5, new ArrayList<Character>());
    }

    /**
     * Escolhe aleatoriamente se irá fazer um ataque normal ou envenenar.
     * Define aleatoriamente também o alvo entre os heróis vivos.
     */
    public void announceIntentions(BattleState battle){
        Random rng = new Random();
        this.next_attack = rng.nextInt(2);
        int choice = rng.nextInt(battle.party.members.size());
        Hero target = battle.party.members.get(choice);
        System.out.println(this.name + " irá atacar " + target.name);
        ArrayList<Character> targets = new ArrayList<Character>();
        targets.add(target);
        setTargets(targets);
        return;
    }

    /**
     * Executa a ação do turno (causa o dano previamente anunciado e/ou aplica veneno) e 
     * constrói a mensagem de registo (actionLog) para exibir ao jogador.
     */
    public void takeTurn(BattleState battle){
        if(this.next_attack == 0){
            if (getTargets().isEmpty())
                return; 
            Hero target = (Hero)getTargets().get(0);
            target.takeDamage(this.getDamage());
            this.setActionLog(this.name + " deu uma pinçada em " + target.name + "!");
            if(target.isAlive()){
                this.setActionLog(this.getActionLog() + "\n" + target.name + " tomou " + this.getDamage() +  " de dano.");
            } else {
                this.setActionLog(this.getActionLog() + "\n" + target.name + " toma " + this.getDamage() + " de dano, e desmaia!");
            }
        }

        if(this.next_attack == 1){
            if (getTargets().isEmpty())
                return; 
            Hero target = (Hero)getTargets().get(0);
            target.takeDamage(1);
            PoisonEffect poison = new PoisonEffect(target, 3, 3);
            target.addEffect(battle.publisher, poison);
            this.setActionLog(this.name + " picou " + target.name + "!");
            if(target.isAlive()){
                this.setActionLog(this.getActionLog() + "\n" + target.name + " tomou 1 de dano.");
            } else {
                this.setActionLog(this.getActionLog() + "\n" + target.name + " toma 1 de dano, e desmaia!");
            }
        }
    }

    /**
     * Imprime no terminal as ações feitas pelo escorpião no seu turno.
     */
    public void printActionLog(){
        System.out.println(this.getActionLog());
    }

}