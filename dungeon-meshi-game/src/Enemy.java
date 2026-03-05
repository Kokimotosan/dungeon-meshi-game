public class Enemy {
    String name;
    int health;
    int shield;

    public Enemy(String name, int health, int shield){
        this.name = name;
        this.health = health;
        this.shield = shield;
    }

    public void attack(Hero target, int dmg){
        target.takeDamage(dmg);
    }

    public void takeDamage(int dmg){
        int remainder = dmg;
        if (this.shield >= dmg){
            this.shield -= dmg;
        } else {
            remainder -= this.shield;
            this.shield = 0;
            this.health -= remainder;
        }
    }


    public boolean isAlive(){
        if (this.health > 0){
            return true;
        }
        return false;
    }
}
