package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/** Caminhos extras de {@link BattleState} e {@link Party#wipeEffects()}. */
public class BattleStateAdvancedTest {

    @Test
    void isFirstEnemyTurnVerdadeiroNoPrimeiroInimigoAposHeroi() {
        Party p = new Party();
        p.addMember(new Hero("H", 10, 10, 0, 1));
        ArrayList<Enemy> es = new ArrayList<>();
        es.add(new WalkingMushroom(1));
        BattleState b = new BattleState(p, es, new Deck(), new Scanner(System.in));
        b.turn = 1;
        assertTrue(b.isFirstEnemyTurn());
    }

    @Test
    void isFirstEnemyTurnFalsoNoSegundoInimigo() {
        Party p = new Party();
        p.addMember(new Hero("H", 10, 10, 0, 1));
        ArrayList<Enemy> es = new ArrayList<>();
        es.add(new WalkingMushroom(1));
        es.add(new HugeScorpion(1));
        BattleState b = new BattleState(p, es, new Deck(), new Scanner(System.in));
        b.turn = 2;
        assertFalse(b.isFirstEnemyTurn());
    }

    @Test
    void printBattleStateEPrintHandNaoLancam() {
        Party p = new Party();
        p.addMember(new Hero("H", 10, 10, 0, 1));
        ArrayList<Enemy> es = new ArrayList<>();
        es.add(new WalkingMushroom(1));
        BattleState b = new BattleState(p, es, new Deck(), new Scanner(System.in));
        b.hand.add(new SwordCard("X", 2, 1));
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            b.printBattleState();
            b.printHand();
        } finally {
            System.setOut(old);
        }
        assertTrue(buf.toString().contains("Batalha"));
        assertTrue(buf.toString().contains("mão"));
    }

    @Test
    void partyWipeEffectsLimpaListaDeEfeitos() {
        Party p = new Party();
        Hero h = new Hero("h", 10, 10, 0, 1);
        h.effects.add(new StrenghtEffect("F", h, 1, 1));
        p.addMember(h);
        p.wipeEffects();
        assertTrue(h.effects.isEmpty());
    }
}
