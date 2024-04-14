package io.github.mayc0n23.ordermanager.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    public void testParseDateFromPatternValidDate() {
        String date = "2022-10-15";
        String pattern = "yyyy-MM-dd";
        LocalDate parsedDate = DateUtils.parseDateFromPattern(date, pattern);
        LocalDate expectedDate = LocalDate.of(2022, 10, 15);
        assertEquals(expectedDate, parsedDate);
    }

    @Test
    public void testParseDateFromPatternInvalidDate() {
        String date = "2022-10-15";
        String pattern = "dd/MM/yyyy";
        assertThrows(DateTimeParseException.class, () -> DateUtils.parseDateFromPattern(date, pattern));
    }

    @Test
    public void testParseDateFromPatternNullDate() {
        String date = null;
        String pattern = "yyyy-MM-dd";
        assertThrows(NullPointerException.class, () -> DateUtils.parseDateFromPattern(date, pattern));
    }

    @Test
    public void testParseDateFromPatternNullPattern() {
        String date = "2022-10-15";
        String pattern = null;
        assertThrows(NullPointerException.class, () -> DateUtils.parseDateFromPattern(date, pattern));
    }

}