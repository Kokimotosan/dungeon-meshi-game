package dungeonmeshigame;

import java.util.ArrayList;

public abstract class Effect extends Subscriber{
    private String name;
    private Character holder;
    private int stacks;


    public Effect(String name, Character holder, int init_stacks){
        this.name = name;
        this.holder = holder;
        this.stacks = init_stacks;
        this.pubs = new ArrayList<Publisher>();
    }

    public void beNotified(){
        this.apply();
    }

    public abstract void apply();

    public abstract Effect mergeEffects();

    public String getString(){
        return (this.name + " (" + this.stacks + ")");
    }

    public int getStacks() {
        return stacks;
    }
    public void setStacks(int stacks) {
        this.stacks = stacks;
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
