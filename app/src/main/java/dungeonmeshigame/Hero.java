package dungeonmeshigame;

public class Hero extends Character {
    public int energy_mod;
    public int draw_ammount;

    public Hero(String name, int health, int max_health, int start_shield, int energy_mod){
        super(name, health, max_health, start_shield);
        this.energy_mod = energy_mod;
    }

    public int getDraw(){
        return draw_ammount;
    }

    public void setDrawAmmount(int draw_ammount){
        this.draw_ammount = draw_ammount;
    }

}
