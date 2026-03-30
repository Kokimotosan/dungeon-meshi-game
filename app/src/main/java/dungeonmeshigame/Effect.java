package dungeonmeshigame;

import java.util.ArrayList;

public abstract class Effect extends Subscriber{
    private String name;
    private Character holder;
    private int duration;
    private int damage;


    public Effect(String name, Character holder, int duration, int damage){
        this.name = name;
        this.holder = holder;
        this.duration = duration;
        this.damage = damage;
        this.pubs = new ArrayList<Publisher>();
    }

    public void beNotified(){
        this.apply();
    }

    public abstract void apply();

    public abstract Effect mergeEffects();

    public String getString(){
        return (this.name + " (" + this.duration + ")");
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Character getHolder() {
        return holder;
    }
    public void setHolder(Character holder) {
        this.holder = holder;
    }
}
