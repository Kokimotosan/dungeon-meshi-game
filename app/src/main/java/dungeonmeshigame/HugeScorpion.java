package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Random;

public class HugeScorpion extends Enemy{
    public int next_attack;

    public HugeScorpion(int index){
        super("Escorpião Gigante " + index, 12, 12, 0,5, new ArrayList<Character>());
    }

    public void announceIntentions(BattleState battle){
        Random attack = new Random();
        Random rng = new Random();
        this.next_attack = attack.nextInt(1);   
        int choice = rng.nextInt(battle.party.members.size());
        Hero target = battle.party.members.get(choice);
        if (this.next_attack == 0)
            System.out.println(this.name + " irá atacar " + target.name + " causando " + this.getDamage() + " de dano");
        else
            System.out.println(this.name + " irá picar " + target.name + " envenenado-o e causando 1 de dano");
        ArrayList<Character> targets = new ArrayList<Character>();
        targets.add(target);
        setTargets(targets);
        return;
    }

    public void takeTurn(BattleState battle){
        if(this.next_attack == 0){
            if (getTargets().isEmpty())
                return; 
            Hero target = (Hero)getTargets().get(0);
            target.takeDamage(this.getDamage());
            this.setActionLog(this.name + " deu uma pinçada em " + target.name + "!");
            if(target.isAlive()){
                this.setActionLog(this.getActionLog() + "\n" + target.name + " tomou " + this.getDamage() +  " de dano.");
            } else {
                this.setActionLog(this.getActionLog() + "\n" + target.name + " toma " + this.getDamage() + " de dano, e desmaia!");
            }
        }

        if(this.next_attack == 1){
            if (getTargets().isEmpty())
                return; 
            Hero target = (Hero)getTargets().get(0);
            target.takeDamage(1);
            PoisonEffect poison = new PoisonEffect(target, 3, 3);
            target.addEffect(battle.publisher, poison);
            this.setActionLog(this.name + " picou " + target.name + "!");
            if(target.isAlive()){
                this.setActionLog(this.getActionLog() + "\n" + target.name + " tomou 1 de dano e foi envenenado.");
            } else {
                this.setActionLog(this.getActionLog() + "\n" + target.name + " toma 1 de dano, e desmaia!");
            }
        }
    }

    public void printActionLog(){
        System.out.println(this.getActionLog());
    }
    
}