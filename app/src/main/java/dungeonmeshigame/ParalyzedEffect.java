package dungeonmeshigame;

import java.util.ArrayList;

public class ParalyzedEffect extends Effect{

    public ParalyzedEffect(Character holder, int duration, int power){
        super("Paralizado", holder, duration, power);
    }

    public Effect mergeEffects(){
        int stacks = 0;
        ArrayList<ParalyzedEffect> merged = new ArrayList<ParalyzedEffect>();
        for(int i = 0; i < this.getHolder().getEffects().size(); i++){
            if(this.getHolder().effects.get(i) instanceof ParalyzedEffect currentParalyzedEffect){
                stacks += currentParalyzedEffect.getDuration();
                merged.add(currentParalyzedEffect);
            }
        }

        for(int i = 0; i < merged.size(); i++){
            merged.get(i).unnapply();
        }

        ParalyzedEffect neweffect = new ParalyzedEffect(getHolder(), stacks, stacks);
        getHolder().effects.add(neweffect);
        return neweffect;
    }

    public void beNotified(Event event){
        if(event == Event.START_HERO_TURN){
            this.apply();
        }
    }

    public void apply(){
        getHolder().
        this.setDuration(this.getDuration() - 1);
    }  

    public void unnapply(){
        getHolder().effects.remove(this);
        getHolder().paralyzed = false;
        for(int i = 0; i < this.pubs.size(); i++){
            this.pubs.get(i).unsubscribe(this);
        }
    }
}
