package dungeonmeshigame;

public class StrenghtEffect extends Effect {
    int duration;

    public StrenghtEffect(Character holder, int power, int duration){
        super("Força", holder, power);
        this.duration = duration;
    }

    public String getString(){
        return (this.getName() + " (" + this.getStacks() + ") por " + this.duration + " turno(s)");
    }


    public Effect mergeEffects(){
        return this;
    }

    public void beNotified(Event event){
        if(event == Event.END_HERO_TURN){
            this.apply();
        }
    }

    public void apply(){
        this.duration -= 1;
        if(this.duration <= 0){
            this.unnapply();
        }
    }    

    public void unnapply(){
        getHolder().effects.remove(this);
        for(int i = 0; i < this.pubs.size(); i++){
            this.pubs.get(i).unsubscribe(this);
        }
    }
}
