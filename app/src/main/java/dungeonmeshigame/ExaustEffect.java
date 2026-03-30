package dungeonmeshigame;

import java.util.ArrayList;

public class ExaustEffect extends Effect{

    public ExaustEffect(Character holder, int power){
        super("Exaustão", holder, power);
    }

    public Effect mergeEffects(){
        int stacks = 0;
        ArrayList<ExaustEffect> merged = new ArrayList<ExaustEffect>();
        for(int i = 0; i < this.getHolder().getEffects().size(); i++){
            if(this.getHolder().effects.get(i) instanceof ExaustEffect currentExaustEffect){
                stacks += currentExaustEffect.getStacks();
                merged.add(currentExaustEffect);
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
        if(event == Event.USE_CARD){
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
