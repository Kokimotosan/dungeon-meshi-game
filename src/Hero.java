public class Hero extends Character {
    public int energy_mod;

    public Hero(String name, int health, int max_health, int start_shield, int energy_mod){
        super(name, health, max_health, start_shield);
        this.energy_mod = energy_mod;
    }
}
