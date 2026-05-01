package dungeonmeshigame;

import java.util.ArrayList;

public class CardCatalog {
    public static ArrayList<Card> catalog;

    public static void createCatalog(){
        if(catalog == null){
            catalog = new ArrayList<Card>();
            catalog.add(new SwordCard("Espada", 3, 1));
            catalog.add(new ShieldCard("Escudo Pequeno", 3, 1));
            catalog.add(new SwordCard("Machado do Senshi", 6, 2));
            catalog.add(new ShieldCard("Panela Inoxidavel do Senshi", 7, 2));
            catalog.add(new EffectCard("Frasco de Veneno", new PoisonEffect("Veneno", null, 2), 1));
            catalog.add(new EffectCard("Antidoto de Escorpiao", new PoisonEffect("Veneno", null, -2), 1));
            catalog.add(new PoisonStingCard("Ferrao de Escorpiao", 2, 1, 1));
            catalog.add(new StrenghtCard("Força", 3, 1, 1));
            catalog.add(new PottedMandragoraCard("Mandragora no Pote", 3, 3, 1));
        }
    }
}
