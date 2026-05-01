package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BattleRoom extends Room {
    private ArrayList<Enemy> enemies;
    private int difficulty;

    public BattleRoom(String name, int difficulty){
        super(name);
        this.enemies = new ArrayList<Enemy>();
        this.difficulty = difficulty;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getNameString(){
        String s = this.getName() + " (" + this.getDifficulty() + ")";
        return s;
    }

    public void processRoom(MapNode current_node){
        Random rng = new Random();
        BattleState currentBattle = new BattleState(GameState.getInstance(), (BattleRoom) current_node.getRoom());
        boolean battle_result = battleLoop(currentBattle);
        if(battle_result == true){

            ArrayList<Card> reward = new ArrayList<Card>();
            for(int i = 0; i < 3; i++){
                Card next_card = CardCatalog.catalog.get(rng.nextInt(CardCatalog.catalog.size()));
                reward.add(next_card);
            }

            pickAReward(reward);

            MapNode next_room = pickNextRoom(GameState.getInstance().getMapRoot(), current_node);
            if(next_room == null){
                System.out.println("Você chegou ao final do masmorro!");
            }
            else{
                next_room.getRoom().processRoom(next_room);
            }
        }
    }

    private static void pickAReward(ArrayList<Card> reward){
            App.clearScreen();
            System.out.println("Você pode escolher uma carta para levar");
            System.out.println();

            for(int i = 0; i < 3; i++){
                System.out.println("(" + (i+1) + ")" + reward.get(i).getName());
                reward.get(i).printCard();
                System.out.println();
            }
            System.out.println("(0) Nenhuma");
            System.out.println("Escolha uma carta para adicionar ao seu deck:");

            int choice = App.receiveInput();
            if(choice > 0 && choice <= 3){
                GameState.getInstance().getDeck().cards.add(reward.get(choice));
                return;
            }
            else if(choice == 0){
                return;
            }
            else{
                pickAReward(reward);
            }
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
    public static boolean battleLoop(BattleState battle){
        Scanner input = new Scanner(System.in);

        Character currentCharacter;

        battle.party.wipeEffects();

        while(!battle.isOver()){
            currentCharacter = battle.getTurnCharacter();

            if(currentCharacter instanceof Hero){
                if(battle.turn == 0){ // Turno do primeiro herói
                    battle.publisher.notifySubs(Event.START_HERO_TURN);
                    for(Enemy enemy : battle.enemies){
                        enemy.setIntentions(battle);
                    }
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
                    App.clearScreen();
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
                    battle.publisher.notifySubs(Event.BEFORE_HERO_ACTION);
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
                            input.nextLine(); 

                        }
                    }
                    takenAction = false;
                }
    
            } else if(currentCharacter instanceof Enemy currentEnemy && currentCharacter.isAlive()){
                App.clearScreen();
                if(battle.isFirstEnemyTurn()){ // Turno do primeiro inimigo
                    battle.publisher.notifySubs(Event.END_HERO_TURN);
                }
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
            System.out.println("Aperte enter para continuar...");
            input.nextLine();
            return true;
        }else{
            System.out.println("Sua equipe foi derrotada...");
            System.out.println("Aperte enter para encerrar.");
            input.nextLine();
            return false;
        }
    }
}
