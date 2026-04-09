package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa um inimigo básico: "Cogumelo Andarilho" (Walking Mushroom).
 * <p>
 * Este é um inimigo simples que realiza ataques físicos diretos (cabeçadas) 
 * contra um alvo aleatório do grupo de heróis.
 * </p>
 */
public class WalkingMushroom extends Enemy{

    /**
     * Construtor do Cogumelo Andarilho.
     * <p>
     * Inicializa o inimigo com 8 pontos de vida e 3 de dano base.
     * </p>
     *
     * @param index O índice numérico para distinguir este cogumelo de outros 
     * possíveis cogumelos presentes na mesma batalha (ex: "Cogumelo Andarilho 1").
     */
    public WalkingMushroom(int index){
        super("Cogumelo Andarilho " + index, 8, 8, 0,3, new ArrayList<Character>());
    }

    public void setIntentions(BattleState battle){
        Random rng = new Random();
        this.setNextAttack(rng.nextInt(2));
        int choice = rng.nextInt(battle.party.members.size());
        Hero target = battle.party.members.get(choice);
        ArrayList<Character> target_list = new ArrayList<Character>();
        target_list.add(target);
        this.setTargets(target_list);
    }

    /**
     * Define aleatoriamente qual herói será o alvo do ataque neste turno e 
     * anuncia a intenção de ataque no ecrã/terminal.
     *
     * @param battle O estado atual da batalha (utilizado para acessar à lista de heróis do grupo).
     */
    public void announceIntentions(BattleState battle){
        Character target = this.getTargets().get(0);
        if(getNextAttack() == 0){
            System.out.println(this.name + " irá dar uma cabeçada em " + target.name + " causando 3 de dano.");
        } else {
            System.out.println(this.name + " irá soltar esporos em " + target.name + " causando Paralisia (1)");
        }
        ArrayList<Character> targets = new ArrayList<Character>();
        targets.add(target);
        setTargets(targets);
        return;
    }

    /**
     * Executa a ação do inimigo (a cabeçada) contra o alvo previamente escolhido.
     * <p>
     * Subtrai os pontos de vida (ou escudo) do herói alvo e constrói a mensagem de registo 
     * (action log) detalhando se o herói sobreviveu ao ataque ou se desmaiou (vida a 0).
     * </p>
     *
     * @param battle O estado atual da batalha.
     */
    public void takeTurn(BattleState battle){
        if(this.getNextAttack() == 0){
            if (getTargets().isEmpty())
                return; 
            Hero target = (Hero)getTargets().get(0);
            target.takeDamage(this.getDamage());
            this.setActionLog(this.name + " deu uma cabeçada em " + target.name + "!");
            if(target.isAlive()){
                this.setActionLog(this.getActionLog() + "\n" + target.name + " tomou " + this.getDamage() +  " de dano.");
            } else {
                this.setActionLog(this.getActionLog() + "\n" + target.name + " toma " + this.getDamage() + " de dano, e desmaia!");
            }
        }

        if(this.getNextAttack() == 1){
            if (getTargets().isEmpty())
                return; 
            Hero target = (Hero)getTargets().get(0);
            ParalysisEffect paralysis = new ParalysisEffect("Paralise", target, 1);
            target.addEffect(battle.publisher, paralysis);
            this.setActionLog(this.name + " soltou esporos em " + target.name + " causando Paralisia (1)");
        }

    }

    /**
     * Imprime no terminal o registo das ações executadas durante o turno deste inimigo.
     */
    public void printActionLog(){
        System.out.println(this.getActionLog());
    }
}