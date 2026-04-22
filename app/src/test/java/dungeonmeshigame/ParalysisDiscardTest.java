package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/** {@link ParalysisEffect#apply}: descarte quando é o turno do portador. */
public class ParalysisDiscardTest {

    @Test
    void paralisiaNoBeforeHeroActionChamaAskForDiscard() {
        Party party = new Party();
        Hero h = new Hero("H", 20, 20, 0, 2);
        party.addMember(h);
        party.energy = 5;
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new WalkingMushroom(1));
        String input = "1\n";
        Scanner scan = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        BattleState b = new BattleState(party, enemies, new Deck(), scan);
        b.turn = 0;
        b.hand.add(new SwordCard("c1", 1, 0));
        b.hand.add(new SwordCard("c2", 1, 0));
        ParalysisEffect p = new ParalysisEffect("P", h, 1);
        b.publisher.subscribe(p);
        b.publisher.notifySubs(Event.BEFORE_HERO_ACTION);
        assertEquals(1, b.hand.size());
    }
}
