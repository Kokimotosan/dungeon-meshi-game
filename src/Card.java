import java.util.Scanner;
import java.util.ArrayList;

public abstract class Card {
    private String name;
    private int cost;
    private String useLog;

    public Card(String name, int cost){
        this.name = name;
        this.cost = cost;
        this.useLog = new String();
    }

    public Card discard(BattleState battle){
        battle.hand.remove(this);
        battle.deck.discard_pile.add(this);
        return this;
    }

    public abstract boolean useCard(BattleState battle, ArrayList<Character> target);

    public abstract void printCard();

    public abstract ArrayList<Character> askForTarget(BattleState battle, Scanner scan);

    public abstract void printUseLog();

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getUseLog() {
        return useLog;
    }

    public void setUseLog(String useLog) {
        this.useLog = useLog;
    }
}
