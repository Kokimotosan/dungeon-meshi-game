import java.util.ArrayList;

public class BattleState{
    Party heroes;
    ArrayList<Enemy> enemies;
    int round;
    int turn;

    public BattleState(Party party, ArrayList<Enemy> enemies){
        this.heroes = party;
        this.enemies = enemies;
        this.round = 1;
        this.turn = 0;
    }

    public Character getTurnCharacter(){
        int counter = 0;
        Character current;
        current = this.heroes.members.get(0);
        while(counter < this.heroes.members.size() && counter <= turn){
            current = this.heroes.members.get(counter);
            counter++;
        }
        while(counter < this.heroes.members.size() + this.enemies.size() && counter <= turn){
            current = this.enemies.get(counter - this.heroes.members.size());
        }
        return current;
    }

    public int getTurnLoop(){
        return this.heroes.members.size() + this.enemies.size();
    }

    public void printBattleState(){
        App.clearScreen();
        System.out.println("===== Batalha =====");
        System.out.println("Rodada " + round);
        for(int i = 0; i < this.heroes.members.size(); i++)
            System.out.println(this.heroes.members.get(i).name + " " + this.heroes.members.get(i).healthString());
        System.out.println("- vs -");
        for(int i = 0; i < this.enemies.size(); i++)
            System.out.println(this.enemies.get(i).name + " " + this.enemies.get(i).healthString());
        System.out.println("===== --------- =====");
    }

    public void passTurn(){
        this.turn += 1 % this.getTurnLoop();
    }
}