package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

/** Personagem abstrato exercitado por {@link Hero}; efeitos e {@link #healthString()}. */
public class CharacterEffectTest {

    @Test
    void healthStringMostraEscudoEEfeitos() {
        Hero h = new Hero("Alvo", 20, 20, 4, 1);
        h.effects.add(new StrenghtEffect("Força", h, 2, 1));
        String s = h.healthString();
        assertTrue(s.contains("20/20"));
        assertTrue(s.contains("Escudo"));
        assertTrue(s.contains("Força"));
    }

    @Test
    void addEffectInscreveMergeNoPublisher() {
        Party party = new Party();
        Hero h = new Hero("H", 15, 15, 0, 5);
        party.addMember(h);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new WalkingMushroom(1));
        BattleState battle = new BattleState(party, enemies, new Deck(), new Scanner(System.in));
        PoisonEffect p = new PoisonEffect("Veneno", h, 2);
        h.addEffect(battle.publisher, p);
        assertFalse(h.effects.isEmpty());
        assertFalse(battle.publisher.subs.isEmpty());
    }
}
