package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;

/** Estado do combate: fim de jogo, iniciativa, mão e passagem de turno/rodada. */
public class BattleStateTest {

    private BattleState setup() {
        Party party = new Party();
        party.addMember(new Hero("H1", 10, 10, 0, 3));
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new WalkingMushroom(1));
        return new BattleState(party, enemies, new Deck(), new Scanner(System.in));
    }

    @Test
    void isOverQuandoTodosHeroisMortos() {
        BattleState b = setup();
        b.party.members.get(0).health = 0;
        assertTrue(b.isOver());
    }

    @Test
    void isOverQuandoTodosInimigosMortos() {
        BattleState b = setup();
        for (Enemy e : b.enemies) {
            e.health = 0;
        }
        assertTrue(b.isOver());
    }

    @Test
    void isOverContinuaComAmbosLadosVivos() {
        BattleState b = setup();
        assertFalse(b.isOver());
    }

    @Test
    void aliveEnemiesSoVivos() {
        BattleState b = setup();
        b.enemies.get(0).health = 0;
        assertTrue(b.aliveEnemies().isEmpty());
    }

    @Test
    void passTurnAvancaRodadaAoCompletarCiclo() {
        BattleState b = setup();
        assertEquals(1, b.round);
        int loop = b.getTurnLoop();
        for (int i = 0; i < loop; i++) {
            b.passTurn();
        }
        assertEquals(2, b.round);
        assertEquals(0, b.turn);
    }

    @Test
    void discardHandMoveCartasParaDescarte() {
        BattleState b = setup();
        Card c = new SwordCard("X", 1, 1);
        b.hand.add(c);
        b.discardHand();
        assertTrue(b.hand.isEmpty());
        assertEquals(1, b.deck.discard_pile.size());
    }

    @Test
    void getTurnCharacterAlternaNaIniciativa() {
        BattleState b = setup();
        assertEquals("H1", b.getTurnCharacter().name);
        b.turn = 1;
        assertTrue(b.getTurnCharacter() instanceof Enemy);
    }

    @Test
    void askForDiscardZeroNaoAlteraMao() {
        BattleState b = setup();
        b.hand.add(new SwordCard("c", 1, 1));
        b.askForDiscard(0);
        assertEquals(1, b.hand.size());
    }
}
