package dungeonmeshigame;

import org.junit.jupiter.api.Test;

/** Utilitários estáticos de {@link App} usados no laço do jogo. */
public class AppUtilTest {

    @Test
    void delayPrintComZeroRetornaRapido() {
        App.delayPrint(0);
    }
}
