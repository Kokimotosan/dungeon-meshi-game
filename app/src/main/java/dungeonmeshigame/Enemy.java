package dungeonmeshigame;

import java.util.ArrayList;

public abstract class Enemy extends Character{
    private String actionLog;
    private int damage;
    private ArrayList<Character> targets;

    public Enemy(String name, int health, int max_health, int start_shield, int damage, ArrayList<Character> targets){
        super(name, health, max_health, start_shield);
        this.damage = damage;
        this.targets = targets;
        this.actionLog = new String();
    }

    public abstract void announceIntentions(BattleState battle);

    public abstract void takeTurn(BattleState battle);

    public abstract void printActionLog();

    public ArrayList<Character> getTargets(){
        return targets;
    }

    public String getActionLog() {
        return actionLog;
    }

    public int getDamage() {
        return damage;
    }

    public void setTargets(ArrayList<Character> targets){
        this.targets = targets;
    }

    public void setActionLog(String actionLog) {
        this.actionLog = actionLog;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
