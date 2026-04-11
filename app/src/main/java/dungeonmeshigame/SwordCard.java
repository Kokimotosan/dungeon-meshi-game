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
public class SwordCard extends Card {
    public int damage;

    /**
     * Construtor da carta de Espada.
     *
     * @param name O nome da carta (ex: "Espada Longa").
     * @param damage O valor base de dano da carta.
     * @param cost O custo de energia necessário para jogar a carta.
     */
    public SwordCard(String name, int damage, int cost){
        super(name, cost);
        this.damage = damage;
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
        target.get(0).takeDamage(damage + getDamageModifiers(battle));
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " tomou " + (this.damage + getDamageModifiers(battle)) + " de dano.");
        return true;
    }

    /**
     * Calcula o dano extra ganho através de efeitos positivos (como Força) aplicados ao utilizador.
     * <p>
     * O método percorre a lista de efeitos do personagem que está a jogar a carta (o turno atual).
     * Se encontrar efeitos do tipo {@link StrenghtEffect}, soma a potência desses efeitos ao dano total.
     * </p>
     *
     * @param battle O estado da batalha (para identificar quem está a jogar).
     * @return O valor total dos modificadores de dano a ser adicionado ao dano base da carta.
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

    /**
     * Imprime no terminal a representação visual da carta, o seu custo e o dano causado.
     */
    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("|Causa " + this.damage + " de dano a um inimigo.");
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
        System.out.println("Escolha uma alvo:");
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