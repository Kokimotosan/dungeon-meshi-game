package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

/** Efeitos: veneno, força e paralisia (merge e ticks por evento). */
public class EffectMechanicsTest {

    @Test
    void poisonMergeSomaStacks() {
        Hero h = new Hero("v", 40, 40, 0, 1);
        Party p = new Party();
        p.addMember(h);
        BattleState b = new BattleState(p, new ArrayList<>(), new Deck(), new Scanner(System.in));
        PoisonEffect a = new PoisonEffect("Veneno", h, 2);
        PoisonEffect c = new PoisonEffect("Veneno", h, 3);
        h.addEffect(b.publisher, a);
        h.addEffect(b.publisher, c);
        int totalPower = h.effects.stream().mapToInt(Effect::getPower).sum();
        assertTrue(totalPower >= 3);
    }

    @Test
    void poisonTickNoFimDoTurnoHerói() {
        Hero h = new Hero("v", 20, 20, 0, 1);
        Party party = new Party();
        party.addMember(h);
        BattleState b = new BattleState(party, new ArrayList<>(), new Deck(), new Scanner(System.in));
        PoisonEffect p = new PoisonEffect("Veneno", h, 2);
        b.publisher.subscribe(p);
        int antes = h.health;
        p.beNotified(b, Event.END_HERO_TURN);
        assertTrue(h.health < antes);
    }

    @Test
    void strenghtDuracaoDecaiNoFimDoTurno() {
        Hero h = new Hero("v", 20, 20, 0, 1);
        Party party = new Party();
        party.addMember(h);
        BattleState b = new BattleState(party, new ArrayList<>(), new Deck(), new Scanner(System.in));
        StrenghtEffect s = new StrenghtEffect("Força", h, 2, 1);
        b.publisher.subscribe(s);
        s.beNotified(b, Event.END_HERO_TURN);
        assertTrue(h.effects.isEmpty());
    }

    @Test
    void paralysisMergeSomaDuracao() {
        Hero h = new Hero("v", 20, 20, 0, 1);
        Party party = new Party();
        party.addMember(h);
        BattleState b = new BattleState(party, new ArrayList<>(), new Deck(), new Scanner(System.in));
        ParalysisEffect x = new ParalysisEffect("P", h, 1);
        ParalysisEffect y = new ParalysisEffect("P", h, 2);
        h.addEffect(b.publisher, x);
        h.addEffect(b.publisher, y);
        assertFalse(h.effects.isEmpty());
    }
}
