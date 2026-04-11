package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Representa uma carta de buff que aumenta a força de um aliado.
 */
public class EffectCard extends Card{
    Effect effect;
    
    public EffectCard(String name, Effect effect, int cost){
        super(name, cost);
        this.effect = effect;
    }

    /**
     * Utiliza a carta gastando a energia do grupo, aplicando o efeito de Força 
     * no herói alvo e enviando a carta para a pilha de descarte.
     * * @param battle O estado atual da batalha.
     * @param target A lista de alvos (neste caso, conterá o herói aliado escolhido).
     * @return true se a carta foi jogada com sucesso, false se não houver energia suficiente.
     */
    public boolean useCard(BattleState battle, ArrayList<Character> target){
        if(battle.party.energy < this.getCost()){
            this.setUseLog("Você não tem energia para usar [" + this.getName() + "]");
            return false;
        }
        this.effect.setHolder(target.get(0));
        target.get(0).addEffect(battle.publisher, this.effect);
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " foi afligido com " + this.effect.getString());
        return true;
    }

    /**
     * Imprime no terminal o registo da ação que acabou de ocorrer.
     */
    public void printUseLog(){
        System.out.println(this.getUseLog());
    }

    /**
     * Imprime no terminal a representação visual da carta e a descrição do seu efeito.
     */
    public void printCard(){
        System.out.println("|===== " + this.getName() + " =====|");
        System.out.println("|" + this.getCost() + " custo de energia");
        System.out.println("|Aplica " + this.effect.getString() + " a um aliado ou oponente");
        System.out.println("|===== " + "-".repeat(this.getName().length()) + " =====|");
    }

    /**
     * Pede ao jogador via terminal que selecione o herói aliado que receberá o bónus de força.
     * <p>
     * O método lista todos os membros da equipa (party) e aguarda que o jogador 
     * insira o número correspondente à sua escolha. Se a escolha for inválida, 
     * a pergunta é repetida.
     * </p>
     * * @param battle O estado da batalha (usado para aceder à lista de aliados).
     * @param scan O scanner utilizado para ler o número digitado pelo jogador.
     * @return Uma lista contendo o aliado escolhido para receber o efeito.
     */
    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        System.out.println("Escolha uma alvo:");

        ArrayList<Character> aux = new ArrayList<Character>();
        aux.addAll(battle.party.members);
        aux.addAll(battle.aliveEnemies());

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