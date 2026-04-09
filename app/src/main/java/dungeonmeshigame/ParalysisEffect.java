package dungeonmeshigame;

import java.util.ArrayList;

public class ParalysisEffect extends Effect{

    public ParalysisEffect(String name, Character holder, int power){
        super(name, holder, power, power);
    }

    public void apply(BattleState battle){
        if(battle.getTurnCharacter() == this.getHolder()){
            battle.askForDiscard(this.getPower());
            this.unnapply();
        }

    }

    public Effect mergeEffects(){
        int stacks = 0;
        ArrayList<ParalysisEffect> merged = new ArrayList<ParalysisEffect>();
        for(int i = 0; i < this.getHolder().getEffects().size(); i++){
            if(this.getHolder().effects.get(i) instanceof ParalysisEffect currentParalysisEffect){
                stacks += currentParalysisEffect.getDuration();
                merged.add(currentParalysisEffect);
            }
        }

        for(int i = 0; i < merged.size(); i++){
            merged.get(i).unnapply();
        }

        ParalysisEffect neweffect = new ParalysisEffect("Paralise", getHolder(), stacks);
        
        getHolder().effects.add(neweffect);
        return neweffect;
    }

    public void beNotified(BattleState battle, Event event){
        if(event == Event.BEFORE_HERO_ACTION){
            this.apply(battle);
        }
    }
}