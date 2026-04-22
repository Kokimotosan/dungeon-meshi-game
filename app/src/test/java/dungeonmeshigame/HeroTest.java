package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Testes do herói via campos e comportamento herdados de {@link Character}. */
public class HeroTest {

    @Test
    void heroiGuardaNomeEnergiaEVidaMaxima() {
        Hero h = new Hero("Laios", 20, 100, 0, 3);
        assertEquals("Laios", h.name);
        assertEquals(100, h.max_health);
        assertEquals(100, h.health); // construtor de Character usa max_health como vida atual
        assertEquals(3, h.energy_mod);
    }

    @Test
    void danoAbsorvidoPeloEscudoAntesDaVida() {
        Hero h = new Hero("Marcille", 50, 50, 10, 2);
        h.takeDamage(7);
        assertEquals(3, h.shield);
        assertEquals(50, h.health);
    }

    @Test
    void danoExcedeEscudoEReduzVida() {
        Hero h = new Hero("Senshi", 40, 40, 5, 1);
        h.takeDamage(12);
        assertEquals(0, h.shield);
        assertEquals(33, h.health);
    }

    @Test
    void heroiMortoQuandoVidaZeroOuMenos() {
        Hero h = new Hero("Chilchuck", 10, 10, 0, 1);
        h.takeDamage(10);
        assertFalse(h.isAlive());
    }

    @Test
    void ganhoDeEscudoSomaAoAtual() {
        Hero h = new Hero("Izutsumi", 30, 30, 2, 2);
        h.gainShield(4);
        assertEquals(6, h.shield);
    }
}
