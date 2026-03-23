public abstract class Enemy extends Character{
    private String actionLog;
    private int damage;

    public Enemy(String name, int health, int max_health, int start_shield, int damage){
        super(name, health, max_health, start_shield);
        this.damage = damage;
        this.actionLog = new String();
    }

    public abstract void takeTurn(BattleState battle);

    public abstract void printActionLog();

    public String getActionLog() {
        return actionLog;
    }

    public int getDamage() {
        return damage;
    }

    public void setActionLog(String actionLog) {
        this.actionLog = actionLog;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
