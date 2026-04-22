package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/** Grupo: membros, energia acumulada e teto por soma dos modificadores. */
public class PartyTest {

    @Test
    void addMemberSomaEnergia() {
        Party p = new Party();
        p.addMember(new Hero("A", 10, 10, 0, 2));
        p.addMember(new Hero("B", 10, 10, 0, 3));
        assertEquals(5, p.energy);
        assertEquals(2, p.members.size());
    }

    @Test
    void getMaxEnergyIgualSomaDosMods() {
        Party p = new Party();
        p.addMember(new Hero("x", 5, 5, 0, 4));
        p.addMember(new Hero("y", 5, 5, 0, 1));
        assertEquals(5, p.getMaxEnergy());
    }

    @Test
    void printEnergyEscreveLinha() {
        Party p = new Party();
        p.addMember(new Hero("h", 1, 1, 0, 2));
        p.energy = 1;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            p.printEnergy();
        } finally {
            System.setOut(old);
        }
        assertTrue(buf.toString().contains("Energia"));
    }
}
