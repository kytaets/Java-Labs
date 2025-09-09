import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testHasAllUniqueChars() {
        assertFalse(Main.hasAllUniqueChars("hello"));
        assertTrue(Main.hasAllUniqueChars("world"));
        assertFalse(Main.hasAllUniqueChars("java"));
        assertTrue(Main.hasAllUniqueChars("cat"));
    }
}
