package dungeonmeshigame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AppTest {
    @Test
    void testAppInstantiates() {
        App app = new App();
        assertNotNull(app, "A classe App deve ser instanciada sem problemas.");
    }
}