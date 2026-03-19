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

    // Função que mostra o estado do combate
    public static void displayBattleState(int round, Character character[]){

    }
    
    public static void main(String[] args) throws Exception {
        Party party = new Party();
        Hero Laios = new Hero("Laios", 20, 20, 2, 2);
        party.addMember(Laios);

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        Enemy mush1 = new WalkingMushroom(1);
        Enemy mush2 = new WalkingMushroom(2);
        enemies.add(mush1);
        enemies.add(mush2);

        Card swordCard = new DamageCard("Espada", 3, 1);

        ShieldCard smallShield = new ShieldCard("Escudo Pequeno", 3, 1);
        Deck deck = new Deck();
        deck.cards.add(swordCard);
        deck.cards.add(swordCard);
        deck.cards.add(smallShield);
        
        BattleState currentBattle = new BattleState(party, enemies, deck);
        
        battleLoop(currentBattle);
    }

    public static void battleLoop(BattleState battle){

       Scanner scan = new Scanner(System.in);

        Character currentCharacter;

        while(!battle.isOver()){
            currentCharacter = battle.getTurnCharacter();
            battle.printBattleState();

            if(currentCharacter instanceof Hero){
                if(battle.turn == 0)
                    battle.deck.draw(battle.hand, 3);
                System.out.println("===== Sua mão =====");
                printHand(battle.hand);
                System.out.println("===== Turno de " + currentCharacter.name + " =====");
                System.out.print("Escolha uma ação:");
                for (int i = 0; i < battle.hand.size(); i++)
                    System.out.println("(" + (i + 1) + ")" + " " + battle.hand.get(i).getName());
                System.out.println("(0) Passe o turno");
                int choice = scan.nextInt();

                boolean turnOver = false;
                while(!turnOver){
                    while (turnOver) {
                        
                    }
                    if (choice != 0){
                        if (battle.hand.get(choice - 1) instanceof DamageCard){
                            battle.selectEnemies(battle.enemies);
                            System.out.println("(0) Retornar");
                            int select = scan.nextInt();
                            if (choice != 0)
                                if (!battle.hand.get(choice - 1).useCard(battle.enemies.get(select - 1), battle.party, battle.deck))
                                    System.out.println("Energia Insuficiente");
                            }
                        }
                    }
                }
            }
        }
        scan.close();
    }

    public static void printHand(ArrayList<Card> hand){
        for(int i = 0; i < hand.size(); i++){
            System.out.println("Carta (" + (i + 1) + ")");
            hand.get(i).printCard();
        }
        System.out.println();
    }
 }

