package dungeonmeshigame;

import java.util.ArrayList;

public abstract class Effect extends Subscriber{
    private String name;
    private Character holder;
    private int duration;
    private int power;


    public Effect(String name, Character holder, int duration, int power){
        this.name = name;
        this.holder = holder;
        this.duration = duration;
        this.power = power;
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

    public int getPower(){
        return power;
    }

    public void setPower(int power){
        this.power = power;
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
