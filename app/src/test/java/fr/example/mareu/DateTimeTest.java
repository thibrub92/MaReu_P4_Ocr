package fr.example.mareu;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.example.mareu.utils.DateTimeConverter;

@RunWith(JUnit4.class)
public class DateTimeTest {

    @Test
    public void timeConverterTest() {
        assertEquals("00h00", DateTimeConverter.getFormattedTime(0,0));
        assertEquals("09h30", DateTimeConverter.getFormattedTime(9,30));
        assertEquals("12h00", DateTimeConverter.getFormattedTime(12,0));
        assertEquals("23h50", DateTimeConverter.getFormattedTime(23,50));
    }

    @Test
    public void dateConverterTest() {
        assertEquals("01/01/2020", DateTimeConverter.getFormattedDate(2020,0,1));
        assertEquals("10/02/2022", DateTimeConverter.getFormattedDate(2022, 1,10));
        assertEquals("12/12/2023", DateTimeConverter.getFormattedDate(2023, 11, 12));
    }
}