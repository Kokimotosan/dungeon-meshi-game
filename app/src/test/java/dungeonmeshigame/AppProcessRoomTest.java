package dungeonmeshigame;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;

// /** Ramo de {@link App#processRoom(GameState, MapNode)} com nó nulo. */
// public class AppProcessRoomTest {

//     @Test
//     void processRoomComNuloImprimeParabens() {
//         Party p = new Party();
//         p.addMember(new Hero("h", 5, 5, 0, 1));
//         GameState g = new GameState(p, 0, new Deck(), new MapNode(0));
//         ByteArrayOutputStream buf = new ByteArrayOutputStream();
//         PrintStream old = System.out;
//         System.setOut(new PrintStream(buf));
//         try {
//             App.processRoom(g, null);
//         } finally {
//             System.setOut(old);
//         }
//         assertTrue(buf.toString().contains("parabéns"));
//     }
// }
