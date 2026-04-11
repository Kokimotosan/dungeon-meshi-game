package dungeonmeshigame;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Representa a estrutura modelo para todas as cartas do jogo.
 */
public abstract class Card {
    private String name;
    private int cost;
    private String description;
    /** O registro em texto do que aconteceu quando a carta foi usada no turno. */
    private String useLog;

    public Card(String name, int cost){
        this.name = name;
        this.cost = cost;
        this.useLog = new String();
    }

    /**
     * Remove esta carta da mão do jogador e a envia para a pilha de descarte do deck.
     * * @param battle O estado atual da batalha ({@link BattleState}), que contém a mão e o deck.
     * @return A própria instância da carta descartada.
     */
    public Card discard(BattleState battle){
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        return this;
    }

    /**
     * Aplica o efeito principal da carta (dano, cura, buff, etc.) aos alvos selecionados.
     * <p>
     * * @param battle O estado atual da batalha.
     * @param target A lista de personagens (heróis ou inimigos) que sofrerão o efeito da carta.
     * @return true se a carta foi usada com sucesso, false caso contrário (ex: falta de energia ou alvo inválido).
     */
    public abstract boolean useCard(BattleState battle, ArrayList<Character> target);

    /**
     * Imprime os detalhes da carta (nome, custo, descrição do efeito) no console.
     * Deve ser implementado pelas classes filhas.
     */
    public abstract void printCard();

    /**
     * Interage com o jogador via terminal para perguntar em quem ele deseja usar a carta.
     * <p>
     * Algumas cartas podem afetar apenas um inimigo, outras podem afetar todos, 
     * ou curar um aliado. A classe filha deve definir como essa pergunta é feita.
     * </p>
     * * @param battle O estado atual da batalha (para listar os alvos válidos).
     * @param scan   O scanner para ler a escolha numérica digitada pelo jogador.
     * @return Uma lista contendo o personagem (ou personagens) escolhido como alvo.
     */
    public abstract ArrayList<Character> askForTarget(BattleState battle, Scanner scan);

    /**
     * Imprime no console a mensagem narrando o efeito que acabou de acontecer.
     * Exemplo: "Laios usou a Espada e causou 5 de dano!".
     */
    public abstract void printUseLog();


    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getUseLog() {
        return useLog;
    }

    public void setUseLog(String useLog) {
        this.useLog = useLog;
    }
}