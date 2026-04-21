package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/** Cartas concretas: custo, alvo, uso na batalha e descarte. */
public class CardTest {

    private BattleState baseBattle() {
        Party party = new Party();
        Hero h = new Hero("Herói", 30, 30, 0, 5);
        party.addMember(h);
        party.energy = 10;
        ArrayList<Enemy> enemies = new ArrayList<>();
        WalkingMushroom m = new WalkingMushroom(1);
        m.health = 20;
        enemies.add(m);
        BattleState b = new BattleState(party, enemies, new Deck(), new Scanner(System.in));
        b.turn = 0;
        return b;
    }

    @Test
    void emptyCardNaoGastaRecursos() {
        EmptyCard e = new EmptyCard();
        BattleState b = baseBattle();
        assertFalse(e.useCard(b, new ArrayList<>()));
        assertTrue(e.askForTarget(b, new Scanner(System.in)).isEmpty());
    }

    @Test
    void discardRemoveDaMaoEEnviaAoMonte() {
        BattleState b = baseBattle();
        SwordCard s = new SwordCard("Espada", 4, 1);
        b.hand.add(s);
        s.discard(b);
        assertTrue(b.hand.isEmpty());
        assertTrue(b.deck.discard_pile.contains(s));
    }

    @Test
    void swordCardSemEnergiaNaoAplica() {
        BattleState b = baseBattle();
        b.party.energy = 0;
        SwordCard s = new SwordCard("E", 5, 2);
        ArrayList<Character> t = new ArrayList<>();
        t.add(b.enemies.get(0));
        assertFalse(s.useCard(b, t));
    }

    @Test
    void swordCardComEnergiaCausaDano() {
        BattleState b = baseBattle();
        SwordCard s = new SwordCard("E", 5, 2);
        b.hand.add(s);
        int hpAntes = b.enemies.get(0).health;
        Scanner scan = new Scanner(new ByteArrayInputStream("1\n".getBytes(StandardCharsets.UTF_8)));
        b.scan = scan;
        ArrayList<Character> alvo = s.askForTarget(b, scan);
        assertTrue(s.useCard(b, alvo));
        assertTrue(b.enemies.get(0).health < hpAntes);
    }

    @Test
    void shieldCardConcedeEscudoAoUsuarioDoTurno() {
        BattleState b = baseBattle();
        ShieldCard sh = new ShieldCard("Esc", 6, 1);
        b.hand.add(sh);
        ArrayList<Character> alvo = sh.askForTarget(b, new Scanner(System.in));
        assertTrue(sh.useCard(b, alvo));
        assertEquals(6, b.party.members.get(0).shield);
    }

    @Test
    void strenghtCardAplicaEfeitoNoAliadoEscolhido() {
        BattleState b = baseBattle();
        StrenghtCard st = new StrenghtCard("Força", 3, 2, 1);
        b.hand.add(st);
        Scanner scan = new Scanner(new ByteArrayInputStream("1\n".getBytes(StandardCharsets.UTF_8)));
        b.scan = scan;
        ArrayList<Character> alvo = st.askForTarget(b, scan);
        assertTrue(st.useCard(b, alvo));
        assertFalse(b.party.members.get(0).effects.isEmpty());
    }

    @Test
    void effectCardAplicaVenenoConfigurado() {
        BattleState b = baseBattle();
        Hero alvo = b.party.members.get(0);
        EffectCard ec = new EffectCard("Frasco", new PoisonEffect("Veneno", alvo, 2), 1);
        b.hand.add(ec);
        Scanner scan = new Scanner(new ByteArrayInputStream("1\n".getBytes(StandardCharsets.UTF_8)));
        b.scan = scan;
        ArrayList<Character> t = ec.askForTarget(b, scan);
        assertTrue(ec.useCard(b, t));
        assertTrue(alvo.effects.stream().anyMatch(e -> e instanceof PoisonEffect));
    }

    @Test
    void poisonStingCausaDanoEEnvenena() {
        BattleState b = baseBattle();
        PoisonStingCard p = new PoisonStingCard("Ferrão", 2, 3, 1);
        b.hand.add(p);
        Enemy e = b.enemies.get(0);
        Scanner scan = new Scanner(new ByteArrayInputStream("1\n".getBytes(StandardCharsets.UTF_8)));
        b.scan = scan;
        ArrayList<Character> t = p.askForTarget(b, scan);
        int hp = e.health;
        assertTrue(p.useCard(b, t));
        assertTrue(e.health < hp);
        assertFalse(e.effects.isEmpty());
    }

    @Test
    void pottedMandragoraDanificaUsuarioEInimigos() {
        BattleState b = baseBattle();
        PottedMandragoraCard pm = new PottedMandragoraCard("Vaso", 4, 2, 1);
        b.hand.add(pm);
        ArrayList<Character> ts = pm.askForTarget(b, new Scanner(System.in));
        int heroHp = b.party.members.get(0).health;
        int enemyHp = b.enemies.get(0).health;
        assertTrue(pm.useCard(b, ts));
        assertTrue(b.party.members.get(0).health < heroHp);
        assertTrue(b.enemies.get(0).health < enemyHp);
    }
}
