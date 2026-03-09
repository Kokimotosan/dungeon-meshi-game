public class ShieldCard {
    int shield;
    int cost;
    
    public ShieldCard(int shield, int cost){
        this.shield = shield;
        this.cost = cost;
    }

    public void UseCard(Hero self){
        self.gainShield(shield);
    }
}
