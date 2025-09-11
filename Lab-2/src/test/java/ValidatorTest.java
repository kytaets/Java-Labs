import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void testDates() {
        LocalDate date1 = Validator.parseDate("01.01.2000");
        LocalDate date2 = Validator.parseDate("31.12.2025");

        assertEquals(LocalDate.of(2000, 1, 1), date1);
        assertEquals(LocalDate.of(2025, 12, 31), date2);

        assertNull(Validator.parseDate("2000-01-01"));
        assertNull(Validator.parseDate("31/12/2020"));
        assertNull(Validator.parseDate("abc"));
    }

    @Test
    void testPhones() {
        assertTrue(Validator.isValidPhone("+380991112233"));
        assertTrue(Validator.isValidPhone("0991112233"));
        assertTrue(Validator.isValidPhone("380501234567"));
        assertFalse(Validator.isValidPhone("12345"));
        assertFalse(Validator.isValidPhone("phone123"));
        assertFalse(Validator.isValidPhone(""));
    }

    @Test
    void testEdgePhoneLengths() {
        assertTrue(Validator.isValidPhone("+1234567890"));
        assertTrue(Validator.isValidPhone("+1234567890123"));
        assertFalse(Validator.isValidPhone("+123456789"));
        assertFalse(Validator.isValidPhone("+12345678901234"));
    }
}
