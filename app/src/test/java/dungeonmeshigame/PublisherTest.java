package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/** Observer: subscrever, notificar e remover subscritores. */
public class PublisherTest {

    static class CountingSub extends Subscriber {
        final AtomicInteger count = new AtomicInteger();

        CountingSub() {
            pubs = new ArrayList<>();
        }

        @Override
        public void beNotified(BattleState battle, Event event) {
            count.incrementAndGet();
        }
    }

    @Test
    void notifySubsChamaTodos() {
        Party party = new Party();
        party.addMember(new Hero("h", 5, 5, 0, 1));
        BattleState battle = new BattleState(party, new ArrayList<>(), new Deck(), new Scanner(System.in));
        Publisher pub = new Publisher(battle);
        CountingSub a = new CountingSub();
        CountingSub b = new CountingSub();
        pub.subscribe(a);
        pub.subscribe(b);
        pub.notifySubs(Event.START_HERO_TURN);
        assertEquals(1, a.count.get());
        assertEquals(1, b.count.get());
    }

    @Test
    void unsubscribeParaNotificacoes() {
        Party party = new Party();
        party.addMember(new Hero("h", 5, 5, 0, 1));
        BattleState battle = new BattleState(party, new ArrayList<>(), new Deck(), new Scanner(System.in));
        Publisher pub = new Publisher(battle);
        CountingSub s = new CountingSub();
        pub.subscribe(s);
        pub.unsubscribe(s);
        pub.notifySubs(Event.END_HERO_TURN);
        assertEquals(0, s.count.get());
    }
}
