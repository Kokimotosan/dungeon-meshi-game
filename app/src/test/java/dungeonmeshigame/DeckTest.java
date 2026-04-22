package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/** Baralho: embaralhar, comprar e reciclar descarte quando o deque esvazia. */
public class DeckTest {

    @Test
    void shuffleReordenaCartas() {
        Deck d = new Deck();
        for (int i = 0; i < 20; i++) {
            d.cards.addLast(new SwordCard("C" + i, 1, 1));
        }
        Set<String> antes = new HashSet<>();
        d.cards.forEach(c -> antes.add(c.getName()));
        d.shuffleDeck();
        Set<String> depois = new HashSet<>();
        d.cards.forEach(c -> depois.add(c.getName()));
        assertEquals(antes, depois); // mesmo conjunto de nomes
        assertEquals(20, d.cards.size());
    }

    @Test
    void drawReciclaDescarteQuandoDequeVazioNaIteracaoSeguinte() {
        Deck d = new Deck();
        d.discard_pile.add(new ShieldCard("S", 2, 1));
        d.discard_pile.add(new SwordCard("T", 1, 1));
        ArrayList<Card> hand = new ArrayList<>();
        d.draw(hand, 2);
        assertEquals(2, hand.size());
        assertTrue(d.cards.isEmpty());
        assertTrue(d.discard_pile.isEmpty());
    }

    @Test
    void reshuffleAllReconstróiDequeAPartirDoDescarte() {
        Deck d = new Deck();
        d.discard_pile.add(new SwordCard("a", 1, 1));
        d.discard_pile.add(new SwordCard("b", 1, 1));
        d.reshuffleAll();
        assertEquals(2, d.cards.size());
        assertTrue(d.discard_pile.isEmpty());
    }
}
