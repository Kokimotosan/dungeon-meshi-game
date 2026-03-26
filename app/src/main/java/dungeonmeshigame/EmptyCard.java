package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Scanner;

public class EmptyCard extends Card {

    public EmptyCard() {
        super("EmptyCard", 0);
    }
    
    public void printCard(){
        return;
    }

    public ArrayList<Character> askForTarget(BattleState battle, Scanner scan){
        return new ArrayList<Character>();
    }

    public boolean useCard(BattleState battle, ArrayList<Character> target){
        return false;
    }

    public void printUseLog(){
        return;
    }
}
