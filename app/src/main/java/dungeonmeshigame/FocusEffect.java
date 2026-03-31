package dungeonmeshigame;

import java.util.ArrayList;

public class FocusEffect extends Effect{

    public FocusEffect(Character holder, int duration, int power){
        super("Focado", holder, duration, power);
    }

    public Effect mergeEffects(){
        int duration = 0;
        int power = 0;
        ArrayList<FocusEffect> merged = new ArrayList<FocusEffect>();
        for(int i = 0; i < this.getHolder().getEffects().size(); i++){
            if(this.getHolder().effects.get(i) instanceof FocusEffect currentFocusEffect){
                duration += currentFocusEffect.getDuration();
                if (currentFocusEffect.getPower() > power)
                    power = currentFocusEffect.getPower();
                merged.add(currentFocusEffect);
            }
        }

        for(int i = 0; i < merged.size(); i++){
            merged.get(i).unnapply();
        }

        PoisonEffect neweffect = new PoisonEffect(getHolder(), duration, power);
        getHolder().effects.add(neweffect);
        return neweffect;
    }

    public void beNotified(Event event){
        if(event == Event.USE_CARD){
            this.apply();
        }
    }

    public void apply(){
    }

    public void unnapply(){
        getHolder().effects.remove(this);
        for(int i = 0; i < this.pubs.size(); i++){
            this.pubs.get(i).unsubscribe(this);
        }
    }    

}
