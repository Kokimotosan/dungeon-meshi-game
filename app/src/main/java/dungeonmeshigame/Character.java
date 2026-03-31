package dungeonmeshigame;

import java.util.ArrayList;

public abstract class Character {
    String name;
    int health;
    int max_health;
    int shield;
    ArrayList<Effect> effects;


    public Character(String name, int health, int max_health, int start_shield){
        this.name = name;
        this.max_health = max_health;
        this.health = max_health;
        this.shield = start_shield;
        this.effects = new ArrayList<Effect>();
    }

    public void takeDamage(int dmg){
        int remainder = dmg;
        if (this.shield >= dmg){
            this.shield -= dmg;
        } else {
            remainder -= this.shield;
            this.shield = 0;
            this.health -= remainder;
        }
    }

    public void gainShield(int shield){
        this.shield += shield;
    }
        
    public boolean isAlive(){
        if(this.health > 0){
            return true;
        }
        return false;
    }

    public void addEffect(Publisher effect_publisher, Effect effect){
        this.effects.add(effect);
        Effect new_effect = effect.mergeEffects();
        effect_publisher.subscribe(new_effect);
        
    }

    public String healthString(){
        String r = ("(" + this.health + "/" + this.max_health + ") ");
        if (this.shield > 0)
            r += ("(" + this.shield + " de Escudo)");
        if(this.effects.size() > 0)
            r += "[";
        for(int i = 0; i < this.effects.size(); i++){
            r += this.effects.get(i).getString();
            if((i+1) < this.effects.size()){
                r += " / ";
            }
        }
        if(this.effects.size() > 0){
            r += "]";
        }
        return r;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }
}
