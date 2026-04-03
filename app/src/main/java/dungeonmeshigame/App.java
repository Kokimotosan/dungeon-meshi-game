package dungeonmeshigame;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * A classe principal responsável por inicializar e executar o jogo Dungeon Meshi.
 * <p>
 * Esta classe atua como o motor do jogo em modo texto, configurando o estado
 * inicial da partida (grupo de heróis, inimigos, deck de cartas) e gerenciando
 * o laço principal de batalha (turnos, ações, verificação de vitória/derrota).
 * </p>
 * * @author [Julio da Silva Telles RA:281275] e [Andre Storti RA:294852]
 * @version 1.4
 */
public class App {
    
    private static final Scanner input = new Scanner(System.in);

    public static int receiveInput(){
        return input.nextInt();
    }

    public static void delayPrint(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }     
    }

    /**
     * Limpa a tela do terminal do jogador para manter a interface organizada.
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch(final Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Ponto de entrada principal do programa.
     * <p>
     * Este método inicializa o herói(s), cria os inimigos,
     * constrói e embaralha o deck inicial de cartas,
     * e inicia a máquina de estados da batalha.
     * </p>
     * * @param args (não utilizado)
     * @throws Exception Caso ocorra algum erro durante a execução dos processos do sistema.
     */
    public static void main(String[] args) throws Exception {
        Party party = new Party();
        Hero Laios = new Hero("Laios", 20, 20, 0, 3);
        party.addMember(Laios);

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        Enemy mush1 = new WalkingMushroom(1);
        Enemy scorp1 = new HugeScorpion(1);
        enemies.add(mush1);
        enemies.add(scorp1);

        Deck deck = new Deck();

        Card strenght_card = new StrenghtCard("Força",  3, 1, 1);
        deck.cards.add(strenght_card);
        Card poison_sting = new PoisonCard("Ferrão de Escorpião", 2, 1, 1);
        deck.cards.add(poison_sting);
        for(int n = 0; n < 4; n++){
            Card new_card = new SwordCard("Espada", 3, 1);
            deck.cards.add(new_card);
        }
        for(int n = 0; n < 4; n++){
            Card new_card = new ShieldCard("Escudo Pequeno", 3, 1);
            deck.cards.add(new_card);
        }

        deck.shuffleDeck();
        
        BattleState currentBattle = new BattleState(party, enemies, deck);
        Publisher battleFlow = new Publisher();
        currentBattle.publisher = battleFlow;
        
        battleLoop(currentBattle);
    }

    /**
     * Controla o fluxo central do combate até que o jogo termine.
     * <p>
     * O método gerencia a alternância de turnos entre os heróis e os inimigos. 
     * Durante o turno do herói, exibe as opções de cartas, gerencia a energia,
     * permite o uso de itens/ataques/cartas e notifica eventos do jogo. 
     * Durante o turno dos inimigos, automatiza seus ataques.
     * Ao final, verifica e exibe a mensagem de vitória ou derrota.
     * </p>
     * * @param battle O objeto {@link BattleState} contendo as informações e o estado atual da batalha.
     */
    public static void battleLoop(BattleState battle){

        Character currentCharacter;

        while(!battle.isOver()){
            currentCharacter = battle.getTurnCharacter();

            if(currentCharacter instanceof Hero){
                if(battle.turn == 0){ // Turno do primeiro herói
                    battle.publisher.notifySubs(Event.START_HERO_TURN);
                    battle.discardHand();
                    battle.party.energy = battle.party.getMaxEnergy();
                    battle.deck.draw(battle.hand, 5);
                    for(int i = 0; i < battle.party.members.size(); i++){
                        battle.party.members.get(i).shield = 0;
                    }
                }

                boolean takenAction = false;
                boolean turnOver = false;
                Card using = new EmptyCard();
                while(!turnOver){
                    clearScreen();
                    using.printUseLog();
                    battle.printBattleState();
                    if(battle.isOver()){
                        turnOver = true;
                        continue;
                    }
                    battle.printHand();
                    for (Enemy enemy : battle.enemies)
                        if (enemy.isAlive())
                            enemy.announceIntentions(battle);
                    System.out.println("\n" + "===== Turno de " + currentCharacter.name + " =====");
                    battle.party.printEnergy();
                    System.out.println("Escolha uma ação:");
                    for (int i = 0; i < battle.hand.size(); i++)
                        System.out.println("(" + (i + 1) + ")" + " " + battle.hand.get(i).getName());
                    System.out.println("(0) Passe o turno");
                    
                    int choice = input.nextInt(); 
                    input.nextLine(); 

                    while (!takenAction) {
                        if (choice != 0){
                            if (choice > battle.hand.size() || choice < 0){
                                System.out.println("Opção invalida!");
                                choice = input.nextInt(); 
                                input.nextLine(); 
                            } else {
                                battle.publisher.notifySubs(Event.USE_CARD);
                                using = battle.hand.get(choice - 1);
                                using.useCard(battle, using.askForTarget(battle, input));
                                takenAction = true;
                            }
                        } else {
                            takenAction = true;
                            turnOver = true;
                            System.out.println("Aperte Enter para encerrar o turno...");
                            battle.publisher.notifySubs(Event.END_HERO_TURN);
                            input.nextLine(); 
                        }
                    }
                    takenAction = false;
                }
    
            } else if(currentCharacter instanceof Enemy currentEnemy && currentCharacter.isAlive()){
                clearScreen();
                currentEnemy.takeTurn(battle);
                battle.printBattleState();
                System.out.println("===== Turno de " + currentEnemy.name + " =====");
                currentEnemy.printActionLog();
                System.out.println("Dê enter para ver o próximo turno");
                input.nextLine(); 
            }

            battle.passTurn();
        }

        boolean one_hero_alive = false;
        for(Character chara:battle.party.members){
            if(chara.isAlive()){
                one_hero_alive = true;
                break;
            }
        }
        if(one_hero_alive){
            System.out.println("Você venceu!");
        }else{
            System.out.println("Sua equipe foi derrotada...");
        }
    }
}