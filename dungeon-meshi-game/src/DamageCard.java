public class DamageCard {
    String name;
    int damage;
    int cost;

    public DamageCard(String name, int damage, int cost){
        this.name = name;
        this.damage = damage;
        this.cost = cost;
    }

    public void useCard(Enemy target){
        target.takeDamage(this.damage);

    }
}
