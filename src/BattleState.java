import java.util.ArrayList;

public class BattleState{
    Party party;
    ArrayList<Enemy> enemies;
    ArrayList<Character> initiative;
    Deck deck;
    ArrayList<Card> hand;
    int round;
    int turn;

    public BattleState(Party party, ArrayList<Enemy> enemies, Deck deck){
        this.party = party;
        this.enemies = enemies;
        this.round = 1;
        this.turn = 0;
        this.initiative = new ArrayList<Character>();
        this.initiative.addAll(party.members);
        this.initiative.addAll(enemies);
        this.deck = deck;
        this.hand = new ArrayList<Card>();
    }

    public boolean isOver(){
        boolean one_hero_alive = false;
        boolean one_enemy_alive = false;
        for(Character chara:this.party.members){
            if(chara.isAlive()){
                one_hero_alive = true;
                break;
            }
        }
        if (!one_hero_alive)
            return true;

        for(Character chara:this.enemies){
            if(chara.isAlive()){
                one_enemy_alive = true;
                break;
            }
        }
        
        if(one_enemy_alive)
            return false;
        else
            return true;
    }

    public void selectEnemies(ArrayList<Enemy> enemies){
        for(int i = 0; i < this.enemies.size(); i++)
            System.out.println("("+ (i + 1) + ")" + " " + this.enemies.get(i).name + " " + this.enemies.get(i).healthString());
    }

    public Character getTurnCharacter(){
        int counter = 0;
        Character current;
        current = this.party.members.get(0);
        while(counter < this.initiative.size() && counter <= turn){
            current = this.initiative.get(counter);
            counter++;
        }
        return current;
    }

    public int getTurnLoop(){
        return this.party.members.size() + this.enemies.size();
    }

    public void printBattleState(){
        System.out.println("===== Batalha =====");
        System.out.println("Rodada " + round);
        for(int i = 0; i < this.party.members.size(); i++)
            System.out.println(this.party.members.get(i).name + " " + this.party.members.get(i).healthString());
        System.out.println("- vs -");
        for(int i = 0; i < this.enemies.size(); i++)
            System.out.println(this.enemies.get(i).name + " " + this.enemies.get(i).healthString());
        System.out.println("===== --------- =====");
    }

    public void passTurn(){
        this.turn += 1 % this.getTurnLoop();
    }
}