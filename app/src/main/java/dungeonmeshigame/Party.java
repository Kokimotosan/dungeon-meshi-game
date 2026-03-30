package dungeonmeshigame;

import java.util.ArrayList;

public class Party {
    ArrayList<Hero> members;
    int draw;
    int energy;

    public Party(){
        this.members = new ArrayList<Hero>();
        this.energy = 0;
    }

    public void addMember(Hero new_member){
        this.members.add(new_member);
        this.energy += new_member.energy_mod;
    }

    public void printEnergy(){
        System.out.println("Energia: (" + this.energy + "/" + this.getMaxEnergy() + ")");
    }

    int getMaxEnergy(){
        int total = 0;
        for(int i = 0; i < this.members.size(); i++)
            total += this.members.get(i).energy_mod;
        return total;
    }

    public int getDraw(){
        return draw;
    }

    public void setDraw(ArrayList<Hero> members){
        for(Hero member : members)
            if (!member.isParalyzed())
                draw += member.energy_mod;
    }
}
