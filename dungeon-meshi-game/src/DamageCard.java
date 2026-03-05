public class DamageCard {
    int damage;
    int cost;

    public DamageCard(int damage, int cost){
        this.damage = damage;
        this.cost = cost;
    }

    public void useCard(Enemy target){
        target.takeDamage(this.damage);
    }
}
