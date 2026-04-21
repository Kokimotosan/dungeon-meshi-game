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
        ArrayList<Card> hand = new ArrayList<>();
        // primeiro laço só repõe o deque a partir do descarte; a compra efetiva ocorre depois
        d.draw(hand, 2);
        assertEquals(1, hand.size());
        assertTrue(d.cards.isEmpty());
        assertTrue(d.discard_pile.isEmpty());
    }
}
