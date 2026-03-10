public class Hero {
    public String name;
    public int health;
    public int max_health;
    public int shield;

    public Hero(String name, int max_health, int start_shield){
        this.name = name;
        this.max_health = max_health;
        this.health = max_health;
        this.shield = start_shield;
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

    public void gainShield(int shield){
        this.shield += shield;
    }

    public boolean isAlive(){
        if(this.health > 0){
            return true;
        }
        return false;

    }
}
