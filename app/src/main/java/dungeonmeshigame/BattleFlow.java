package dungeonmeshigame;

import java.util.ArrayList;

public class BattleFlow {
    public ArrayList<Publisher> events;
    
    public BattleFlow(){
        this.events = new ArrayList<Publisher>();
    }

    public void notifyEvent(Publisher event){
        event.notify();
    }
}
