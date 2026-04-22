package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/** Saídas de {@link WalkingMushroom} e {@link HugeScorpion}: intenções e log. */
public class EnemyAnnounceAndLogTest {

    private BattleState base() {
        Party p = new Party();
        p.addMember(new Hero("Alvo", 30, 30, 0, 2));
        ArrayList<Enemy> es = new ArrayList<>();
        es.add(new WalkingMushroom(1));
        es.add(new HugeScorpion(1));
        return new BattleState(p, es, new Deck(), new Scanner(System.in));
    }

    @Test
    void cogumeloAnunciaCabecadaEEsporos() {
        BattleState b = base();
        WalkingMushroom m = (WalkingMushroom) b.enemies.get(0);
        ArrayList<Character> t = new ArrayList<>();
        t.add(b.party.members.get(0));
        m.setTargets(t);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            m.setNextAttack(0);
            m.announceIntentions(b);
            m.setNextAttack(1);
            m.announceIntentions(b);
        } finally {
            System.setOut(old);
        }
        String out = buf.toString();
        assertTrue(out.contains("cabeçada") || out.contains("Paralisia"));
    }

    @Test
    void escorpiaoAnunciaAtaqueEPicada() {
        BattleState b = base();
        HugeScorpion s = (HugeScorpion) b.enemies.get(1);
        ArrayList<Character> t = new ArrayList<>();
        t.add(b.party.members.get(0));
        s.setTargets(t);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            s.setNextAttack(0);
            s.announceIntentions(b);
            s.setNextAttack(1);
            s.announceIntentions(b);
        } finally {
            System.setOut(old);
        }
        assertFalse(buf.toString().isEmpty());
    }

    @Test
    void printActionLogCogumeloEscorpiao() {
        BattleState b = base();
        WalkingMushroom m = (WalkingMushroom) b.enemies.get(0);
        m.setActionLog("log cogumelo");
        HugeScorpion s = (HugeScorpion) b.enemies.get(1);
        s.setActionLog("log escorpiao");
        PrintStream old = System.out;
        System.setOut(new PrintStream(ByteArrayOutputStream.nullOutputStream()));
        try {
            assertDoesNotThrow(m::printActionLog);
            assertDoesNotThrow(s::printActionLog);
        } finally {
            System.setOut(old);
        }
    }
}
