import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JournalEntryTest {

    @Test
    void testJournalEntryCreationAndToString() {
        LocalDate birthDate = LocalDate.of(2000, 1, 15);
        JournalEntry entry = new JournalEntry("Іваненко", "Іван", birthDate, "+380991112233", "вул. Шевченка, 10, кв.5");

        String str = entry.toString();
        assertAll(
                () -> assertTrue(str.contains("Іваненко")),
                () -> assertTrue(str.contains("Іван")),
                () -> assertTrue(str.contains("2000-01-15")),
                () -> assertTrue(str.contains("+380991112233")),
                () -> assertTrue(str.contains("Шевченка"))
        );
    }

    @Test
    void testEmptyFieldsNotAllowed() {
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        JournalEntry entry = new JournalEntry("Петренко", "Петро", birthDate, "0991234567", "вул. Лесі Українки, 5");

        assertNotNull(entry);
        assertNotEquals("", entry.toString());
    }

    @Test
    void testMultipleEntries() {
        JournalEntry e1 = new JournalEntry("Сидоренко", "Сидір", LocalDate.of(1999, 12, 31), "+380501112233", "вул. Центральна, 1");
        JournalEntry e2 = new JournalEntry("Коваленко", "Коля", LocalDate.of(2001, 3, 10), "+380631234567", "вул. Лугова, 7, кв.3");

        assertAll(
                () -> assertNotEquals(e1.toString(), e2.toString()),
                () -> assertTrue(e1.toString().contains("Сидоренко")),
                () -> assertTrue(e2.toString().contains("Коваленко"))
        );
    }
}
