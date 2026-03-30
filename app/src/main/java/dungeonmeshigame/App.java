package dungeonmeshigame;

import java.util.Scanner;
import java.util.ArrayList;

public class App {
    // Funções de uso auxiliar
    private static final Scanner input = new Scanner(System.in);
    public static int receiveInput(){
        return input.nextInt();
    }

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
    
    public static void main(String[] args) throws Exception {
        Party party = new Party();
        Hero Laios = new Hero("Laios", 20, 20, 0, 3);
        party.addMember(Laios);

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        Enemy mush1 = new WalkingMushroom(1);
        Enemy mush2 = new WalkingMushroom(2);
        enemies.add(mush1);
        enemies.add(mush2);

        Deck deck = new Deck();

        for(int n = 0; n < 5; n++){
            Card new_card = new DamageCard("Espada", 3, 1);
            deck.cards.add(new_card);
        }
        for(int n = 0; n < 5; n++){
            Card new_card = new ShieldCard("Escudo Pequeno", 3, 1);
            deck.cards.add(new_card);
        }

        deck.shuffleDeck();
        
        BattleState currentBattle = new BattleState(party, enemies, deck);
        
        battleLoop(currentBattle);
    }

public static void battleLoop(BattleState battle){

        Character currentCharacter;

        while(!battle.isOver()){
            currentCharacter = battle.getTurnCharacter();

            if(currentCharacter instanceof Hero){
                if(battle.turn == 0){ // Turno do primeiro herói
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
    }
 }

