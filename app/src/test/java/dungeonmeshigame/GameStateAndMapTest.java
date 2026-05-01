package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/** {@link GameState}, mapa ({@link MapNode}, {@link MapFactory}) e salas ({@link RoomFactory}, {@link BattleRoom}). */
public class GameStateAndMapTest {

    @Test
    void mapFactoryGeraArvoreESalas() {
        MapFactory mf = new MapFactory();
        for (int i = 0; i < 8; i++) {
            MapNode root = mf.floorOneMap(2, 0);
            assertNotNull(root.getFirst_child());
            assertNotNull(root.getSecond_child());
            assertNotNull(root.getFirst_child().getRoom());
        }
    }

    @Test
    void roomFactoryCriaBatalhaComInimigos() {
        RoomFactory rf = new RoomFactory();
        for (int i = 0; i < 15; i++) {
            BattleRoom br = rf.randomFloorOneBattleRoom(16);
            assertNotNull(br.getEnemies());
            assertTrue(br.getDifficulty() >= 0);
            assertTrue(br.getNameString().contains("Batalha"));
        }
    }

    @Test
    void mapNodeQuatroArgumentosEImpressaoArvore() {
        MapNode parent = new MapNode(0);
        MapNode c1 = new MapNode(1, 1, parent);
        MapNode c2 = new MapNode(2, 1, parent);
        MapNode n = new MapNode(5, 1, parent, c1, c2);
        assertSame(c1, n.getFirst_child());
        assertSame(c2, n.getSecond_child());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            n.printTree();
        } finally {
            System.setOut(old);
        }
        assertTrue(buf.toString().contains("->"));
    }

    @Test
    void mapNodeGetStringComSalaEAtalho() {
        MapNode leaf = new MapNode(99, 3, null);
        leaf.setRoom(new BattleRoom("Boss", 10));
        MapNode mid = new MapNode(2, 1, null);
        mid.setRoom(new BattleRoom("Luta", 4));
        mid.setShortcut(leaf);
        String s = mid.getString();
        assertTrue(s.contains("Sala 2"));
        assertTrue(s.contains("Luta"));
        assertTrue(s.contains("->"));
    }
}
