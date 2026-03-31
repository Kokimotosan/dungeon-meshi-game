package dungeonmeshigame;

import java.util.ArrayList;

/**
 * Classe abstrata que define um Inimigo no jogo, sendo uma extensão de {@link Character}.
 * Os inimigos gerem o seu próprio registo de ações e danos, e possuem uma Inteligência
 * Artificial básica para a seleção de alvos e execução dos turnos.
 */
public abstract class Enemy extends Character{
    private String actionLog;
    private int damage;
    private ArrayList<Character> targets;

    /**
     * Construtor base para os inimigos.
     * * @param name Nome do inimigo.
     * @param health Vida inicial do inimigo.
     * @param max_health Vida máxima do inimigo.
     * @param start_shield Escudo com o qual o inimigo começa a batalha.
     * @param damage Dano base que o inimigo inflige.
     * @param targets Lista de alvos do inimigo na rodada atual.
     */
    public Enemy(String name, int health, int max_health, int start_shield, int damage, ArrayList<Character> targets){
        super(name, health, max_health, start_shield);
        this.damage = damage;
        this.targets = targets;
        this.actionLog = new String();
    }

    /**
     * Define o alvo e o tipo de ataque que o inimigo tenciona fazer no turno,
     * avisando o jogador antes que a ação ocorra.
     * * @param battle O estado da batalha atual.
     */
    public abstract void announceIntentions(BattleState battle);

    /**
     * Executa a ação do inimigo com base nas intenções previamente anunciadas.
     * * @param battle O estado da batalha atual.
     */
    public abstract void takeTurn(BattleState battle);

    /**
     * Imprime no ecrã um registo das ações executadas pelo inimigo.
     */
    public abstract void printActionLog();

    public ArrayList<Character> getTargets(){ return targets; }
    public void setTargets(ArrayList<Character> targets){ this.targets = targets; }

    public String getActionLog() { return actionLog; }
    public void setActionLog(String actionLog) { this.actionLog = actionLog; }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
}