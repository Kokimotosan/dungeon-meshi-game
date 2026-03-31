package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Representa uma carta de benefício (buff) que aumenta a força de um aliado.
 * <p>
 * Ao ser jogada, esta carta aplica um efeito de estado positivo (Força) 
 * que aumenta o dano causado pelo herói alvo durante um determinado número de rodadas.
 * </p>
 */
public class StrenghtCard extends Card{
    int power;
    int duration;
    
    /**
     * Construtor da carta de Força.
     * * @param name O nome da carta.
     * @param power O valor do aumento de dano (potência do efeito).
     * @param duration A quantidade de rodadas que o efeito dura.
     * @param cost O custo em energia para jogar a carta.
     */
    public StrenghtCard(String name, int power, int duration, int cost){
        super(name, cost);
        this.power = power;
        this.duration = duration;
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
        StrenghtEffect str_effect = new StrenghtEffect(target.get(0), this.power, this.duration);
        target.get(0).addEffect(battle.publisher, str_effect);
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        battle.party.energy -= this.getCost();
        this.setUseLog("Usou [" + this.getName() + "]: " + target.get(0).name + " foi afligido com " + str_effect.getString());
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
        System.out.println("|Aflige um aliado com Força (" + this.power + ") por " + this.duration + " rodada(s).");
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
        for(int i = 0; i < battle.party.members.size(); i++){
            System.out.println("(" + (i+1) + ") " + battle.party.members.get(i).name);
        }
        int choice = scan.nextInt();
        if (choice >= 1 && choice <= battle.party.members.size()){
            ArrayList<Character> return_list = new ArrayList<Character>();
            return_list.add(battle.party.members.get(choice-1));
            return return_list;
        } else {
            System.out.println("Escolha inválida!");
            return this.askForTarget(battle, scan);
        }
    }    
}