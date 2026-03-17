import java.util.ArrayList;

public abstract class Enemy extends Character{
    public Enemy(String name, int health, int max_health, int start_shield){
        super(name, health, max_health, start_shield);
    }

    public abstract void takeTurn(ArrayList<Enemy> enemies, Party party);
}
