package dungeonmeshigame;

import java.util.ArrayList;

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
}
