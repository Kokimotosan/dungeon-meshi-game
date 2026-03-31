package dungeonmeshigame;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Representa a estrutura base (molde) para todas as cartas do jogo.
 * <p>
 * Como é uma classe abstrata, ela não pode ser instanciada diretamente. 
 * Em vez disso, ela define os atributos comuns (nome, custo de energia) e 
 * obriga as classes filhas (ataques, defesas, magias) a implementarem seus 
 * próprios efeitos e formas de interagir com o jogador.
 * </p>
 */
public abstract class Card {
    private String name;
    private int cost;
    /** O registro em texto do que aconteceu quando a carta foi usada no turno. */
    private String useLog;

    /**
     * Construtor base para criar uma nova carta.
     * <p>
     * Inicializa o nome e o custo. O registro de uso ({@code useLog}) é 
     * inicializado como uma string vazia por padrão.
     * </p>
     * * @param name O nome da carta.
     * @param cost O custo em pontos de energia para usar a carta.
     */
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
     * Este método deve ser implementado pelas classes filhas para definir
     * o que a carta realmente faz no jogo.
     * </p>
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

    /**
     * Obtém o nome da carta.
     * * @return O nome da carta.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o custo de energia da carta.
     * * @return O valor numérico do custo.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Altera o nome da carta.
     * * @param name O novo nome desejado.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Altera o custo de energia da carta.
     * * @param cost O novo valor de custo.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Obtém a mensagem de log da última vez que a carta foi usada.
     * * @return A string contendo o registro da ação.
     */
    public String getUseLog() {
        return useLog;
    }

    /**
     * Define a mensagem narrativa que explica o efeito da carta após seu uso.
     * * @param useLog A string descrevendo o evento.
     */
    public void setUseLog(String useLog) {
        this.useLog = useLog;
    }
}