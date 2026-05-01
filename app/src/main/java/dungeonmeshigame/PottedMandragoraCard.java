package dungeonmeshigame;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Representa uma carta de ataque básico (Ataque Físico/Espada).
 * <p>
 * Esta carta tem como objetivo causar dano direto a um único inimigo. 
 * O dano final infligido é calculado somando o dano base da carta com 
 * quaisquer bónus de força (buffs) que o herói possua no momento.
 * </p>

 */
public class PottedMandragoraCard extends SwordCard {
    int self_damage;

    /**
     * Construtor da carta de Espada.
     *
     * @param name O nome da carta (ex: "Espada Longa").
     * @param damage O valor base de dano da carta.
     * @param cost O custo de energia necessário para jogar a carta.
     */
    public PottedMandragoraCard(String name, int damage, int self_damage, int cost){
        super(name, damage, cost);
        this.self_damage = self_damage;
    }

    public PottedMandragoraCard(PottedMandragoraCard cloned){
        super(cloned.getName(), cloned.damage, cloned.getCost());
        this.self_damage = cloned.self_damage;
    }

    public PottedMandragoraCard clone(){
        return new PottedMandragoraCard(this);
    }

    /**
     * Utiliza a carta gastando a energia do grupo, aplicando o dano (base + modificadores) 
     * ao inimigo alvo e enviando a carta para a pilha de descarte.
     *
     * @param battle O estado atual da batalha.
     * @param target A lista de alvos (conterá o inimigo escolhido pelo jogador).
     * @return true se a carta foi jogada com sucesso, false se não houver energia suficiente.
     */
    public boolean useCard(BattleState battle, ArrayList<Character> target){
        if(battle.party.energy < this.getCost()){
            this.setUseLog("Você não tem energia para usar [" + this.getName() + "]");
            return false;
        }

        target.get(0).takeDamage(self_damage);
        for(int i = 1; i < target.size(); i++){
            target.get(i).takeDamage(damage + getDamageModifiers(battle));
        }

        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: todos os inimigos tomaram " + (this.damage + getDamageModifiers(battle)) + " de dano.\n");
        this.setUseLog(this.getUseLog()+ "... Mas " + target.get(0).name + " também tomou " + this.self_damage + " de dano.");
        return true;
    }


    /**
     * Imprime no terminal a representação visual da carta, o seu custo e o dano causado.
     */
    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("|Causa " + this.damage + " de dano a todos os inimigos");
        System.out.println("|Mas o usuário também toma " + self_damage + " de dano");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }

    /**
     * Imprime no terminal o registo da ação que acabou de ocorrer (ex: dano infligido).
     */
    public void printUseLog(){
        System.out.println(this.getUseLog());
    }

    /**
     * Pede ao jogador via terminal que selecione o inimigo alvo para o ataque.
     * <p>
     * O método constrói primeiro uma lista auxiliar apenas com os inimigos que ainda 
     * estão vivos. Depois, apresenta essas opções e aguarda que o jogador insira o 
     * número correspondente. Se a escolha for inválida, a pergunta é repetida de forma recursiva.
     * </p>
     *
     * @param battle O estado da batalha (usado para aceder à lista de inimigos).
     * @param scan O scanner utilizado para ler a escolha numérica digitada pelo jogador.
     * @return Uma lista contendo o inimigo escolhido como alvo do ataque.
     */
    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        ArrayList<Character> targets = new ArrayList<Character>();

        targets.add(battle.getTurnCharacter());
        targets.addAll(battle.enemies);

        return targets;
    }
}