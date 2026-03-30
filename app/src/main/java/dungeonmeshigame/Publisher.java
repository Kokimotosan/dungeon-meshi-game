package dungeonmeshigame;

import java.util.ArrayList;

public class Publisher{
    ArrayList<Subscriber> subs;

    public Publisher(){
        this.subs = new ArrayList<Subscriber>();
    }

    public Subscriber subscribe(Subscriber sub){
        this.subs.add(sub);
        sub.pubs.add(this);
        return sub;
    }

    public Subscriber unsubscribe(Subscriber sub){
        this.subs.remove(sub);
        return sub;
    }

    public void notifySubs(Event event){
        for(int i = 0; i < this.subs.size(); i++){
            this.subs.get(i).beNotified(event);
        }
    }
}
