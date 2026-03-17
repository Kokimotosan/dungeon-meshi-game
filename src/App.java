import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayDeque;

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
        Hero Laios = new Hero("Laios", 20, 20, 0, 2);
        party.addMember(Laios);

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        Enemy mushroom;

        ArrayList<Card> hand = new ArrayList<Card>();


    }
 }

