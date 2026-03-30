package dungeonmeshigame;

import java.util.ArrayList;
import java.util.Random;

public class WalkingMushroom extends Enemy{

    public WalkingMushroom(int index){
        super("Cogumelo Andarilho " + index, 8, 8, 0,3, new ArrayList<Character>());
    }

    public void announceIntentions(BattleState battle){
        Random rng = new Random();
        int choice = rng.nextInt(battle.party.members.size());
        Hero target = battle.party.members.get(choice);
        System.out.println(this.name + " irá dar uma cabeçada em " + target.name);
        ArrayList<Character> targets = new ArrayList<Character>();
        targets.add(target);
        setTargets(targets);
    }

    public void takeTurn(BattleState battle){
        if (getTargets().isEmpty())
            return; 
        Hero target = (Hero)getTargets().get(0);
        target.takeDamage(this.getDamage());
        this.setActionLog(this.name + " deu uma cabeçada em " + target.name + "!");
        if(target.isAlive()){
            this.setActionLog(this.getActionLog() + "\n" + target.name + " tomou " + this.getDamage() +  " de dano.");
        } else {
            this.setActionLog(this.getActionLog() + "\n" + target.name + " toma " + this.getDamage() + " de dano, e desmaia!");
        }
    }

    public void printActionLog(){
        System.out.println(this.getActionLog());
    }
}