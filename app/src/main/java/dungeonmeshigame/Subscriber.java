package dungeonmeshigame;

import java.util.ArrayList;

public abstract class Subscriber {
    ArrayList<Publisher> pubs;
    
    public abstract void beNotified(Event event);
}
