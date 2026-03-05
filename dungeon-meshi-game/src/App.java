public class App {
    public static void main(String[] args) throws Exception {
        Hero new_hero = new Hero("Laios", 20, 0);
        System.out.println(new_hero.name);
        /*
        System.out.println(new_hero.health);
        new_hero.takeDamage(3); // health = 17
        new_hero.gainShield(5);
        new_hero.takeDamage(3); // hp = 17, shield = 2
        System.out.println(new_hero.health +  " " + new_hero.shield);
        new_hero.takeDamage(5); // 14;
        System.out.println(new_hero.health +  " " + new_hero.shield);
        */
    }
}
