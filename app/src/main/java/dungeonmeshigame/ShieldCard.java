package dungeonmeshigame;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Representa uma carta de defesa que concede pontos de escudo ao utilizador.
 * <p>
 * O escudo gerado por esta carta atua como uma barreira temporária que 
 * absorve o dano dos ataques inimigos antes que os pontos de vida do personagem 
 * sejam afetados.
 * </p>
 */
public class ShieldCard extends Card {
    int shield;
    
    /**
     * Construtor da carta de escudo.
     * * @param name O nome da carta (ex: "Escudo Pequeno").
     * @param shield O valor de defesa (escudo) fornecido pela carta.
     * @param cost O custo de energia necessário para jogar a carta.
     */
    public ShieldCard(String name, int shield, int cost){
        super(name, cost);
        this.shield = shield;
    }

    /**
     * Utiliza a carta gastando a energia do grupo, aplicando os pontos de escudo 
     * ao alvo e movendo a carta para a pilha de descarte.
     * * @param battle O estado atual da batalha.
     * @param target A lista de alvos (neste caso, conterá apenas o utilizador da carta).
     * @return true se a carta foi jogada com sucesso, false se não houver energia suficiente.
     */
    public boolean useCard(BattleState battle, ArrayList<Character> target){
        if(battle.party.energy < this.getCost()){
            this.setUseLog("Você não tem energia para usar [" + this.getName() + "]");
            return false;
        }
        target.get(0).gainShield(shield);
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " recebeu " + this.shield + " de escudo");
        return true;
    }

    /**
     * Imprime no terminal o registo do que aconteceu quando a carta foi jogada.
     */
    public void printUseLog(){
        System.out.println(this.getUseLog());
    }

    /**
     * Imprime no terminal a representação visual da carta e a descrição dos seus efeitos.
     */
    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("|Gera " + this.shield + " de escudo para o usuário");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }

    /**
     * Define quem será o alvo do efeito da carta.
     * <p>
     * Como as cartas de escudo afetam o próprio utilizador, este método 
     * não pede entrada do jogador pelo terminal (Scanner) e devolve automaticamente 
     * o personagem que está a jogar o turno atual.
     * </p>
     * * @param battle O estado da batalha (usado para descobrir de quem é o turno).
     * @param scan O scanner de leitura do terminal (ignorado neste método).
     * @return Uma lista contendo apenas o personagem que jogou a carta.
     */
    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        ArrayList<Character> returnList = new ArrayList<Character>();
        returnList.add(battle.getTurnCharacter());
        return returnList;
    }
}