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

    /**
     * Define aleatoriamente qual herói será o alvo do ataque neste turno e 
     * anuncia a intenção de ataque no ecrã/terminal.
     *
     * @param battle O estado atual da batalha (utilizado para aceder à lista de heróis do grupo).
     */
    public void announceIntentions(BattleState battle){
        Random rng = new Random();
        int choice = rng.nextInt(battle.party.members.size());
        Hero target = battle.party.members.get(choice);
        System.out.println(this.name + " irá dar uma cabeçada em " + target.name + " causando " + this.getDamage() + " de dano ");
        ArrayList<Character> targets = new ArrayList<Character>();
        targets.add(target);
        setTargets(targets);
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

    /**
     * Imprime no terminal o registo das ações executadas durante o turno deste inimigo.
     */
    public void printActionLog(){
        System.out.println(this.getActionLog());
    }
}