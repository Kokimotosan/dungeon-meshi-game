package dungeonmeshigame;

import java.util.ArrayList;

public class PoisonEffect extends Effect{


    public PoisonEffect(Character holder, int power){
        super("Veneno", holder, power);
    }

    public Effect mergeEffects(){
        int stacks = 0;
        ArrayList<PoisonEffect> merged = new ArrayList<PoisonEffect>();
        for(int i = 0; i < this.getHolder().getEffects().size(); i++){
            if(this.getHolder().effects.get(i) instanceof PoisonEffect currentPoisonEffect){
                stacks += currentPoisonEffect.getStacks();
                merged.add(currentPoisonEffect);
            }
        }

        for(int i = 0; i < merged.size(); i++){
            merged.get(i).unnapply();
        }

        PoisonEffect neweffect = new PoisonEffect(getHolder(), stacks);
        getHolder().effects.add(neweffect);
        return neweffect;
    }

    public void beNotified(Event event){
        if(event == Event.END_HERO_TURN){
            this.apply();
        }
    }

    public void apply(){
        getHolder().health -= getStacks();
        this.setStacks(this.getStacks() - 1);
    }    

    public void unnapply(){
        getHolder().effects.remove(this);
        for(int i = 0; i < this.pubs.size(); i++){
            this.pubs.get(i).unsubscribe(this);
        }
    }
}