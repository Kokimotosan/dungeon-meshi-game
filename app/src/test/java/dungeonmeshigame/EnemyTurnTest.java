package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

/** Inimigos: intenções e turno com ataque fixado por setters (sem depender de RNG). */
public class EnemyTurnTest {

    private BattleState battleComUmHeroi() {
        Party p = new Party();
        Hero h = new Hero("Laios", 20, 20, 0, 3);
        p.addMember(h);
        ArrayList<Enemy> es = new ArrayList<>();
        es.add(new WalkingMushroom(1));
        return new BattleState(p, es, new Deck(), new Scanner(System.in));
    }

    @Test
    void cogumeloCabecadaReduzVidaDoAlvo() {
        BattleState b = battleComUmHeroi();
        WalkingMushroom m = (WalkingMushroom) b.enemies.get(0);
        Hero h = b.party.members.get(0);
        m.setNextAttack(0);
        ArrayList<Character> alvo = new ArrayList<>();
        alvo.add(h);
        m.setTargets(alvo);
        int vida = h.health;
        m.takeTurn(b);
        assertTrue(h.health < vida);
        assertTrue(m.getActionLog().contains("cabeçada"));
    }

    @Test
    void cogumeloEsporosAplicaParalisia() {
        BattleState b = battleComUmHeroi();
        WalkingMushroom m = (WalkingMushroom) b.enemies.get(0);
        m.setNextAttack(1);
        ArrayList<Character> alvo = new ArrayList<>();
        alvo.add(b.party.members.get(0));
        m.setTargets(alvo);
        m.takeTurn(b);
        assertFalse(b.party.members.get(0).effects.isEmpty());
    }

    @Test
    void escorpiaoPinçadaCausaDano() {
        Party p = new Party();
        p.addMember(new Hero("h", 25, 25, 0, 2));
        ArrayList<Enemy> es = new ArrayList<>();
        HugeScorpion s = new HugeScorpion(1);
        es.add(s);
        BattleState b = new BattleState(p, es, new Deck(), new Scanner(System.in));
        s.setNextAttack(0);
        ArrayList<Character> alvo = new ArrayList<>();
        alvo.add(p.members.get(0));
        s.setTargets(alvo);
        int hp = p.members.get(0).health;
        s.takeTurn(b);
        assertTrue(p.members.get(0).health < hp);
    }

    @Test
    void escorpiaoPicadaAplicaVeneno() {
        Party p = new Party();
        p.addMember(new Hero("h", 25, 25, 0, 2));
        ArrayList<Enemy> es = new ArrayList<>();
        HugeScorpion s = new HugeScorpion(1);
        es.add(s);
        BattleState b = new BattleState(p, es, new Deck(), new Scanner(System.in));
        s.setNextAttack(1);
        ArrayList<Character> alvo = new ArrayList<>();
        alvo.add(p.members.get(0));
        s.setTargets(alvo);
        s.takeTurn(b);
        assertTrue(p.members.get(0).effects.stream().anyMatch(e -> e instanceof PoisonEffect));
    }
}
