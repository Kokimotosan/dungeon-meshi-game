public class ShieldCard {
    String name;
    int shield;
    int cost;
    
    public ShieldCard(String name, int shield, int cost){
        this.name = name;
        this.shield = shield;
        this.cost = cost;
    }

    public void UseCard(Hero self){
        self.gainShield(shield);
    }
}
