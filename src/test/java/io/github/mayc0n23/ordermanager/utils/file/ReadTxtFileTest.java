package io.github.mayc0n23.ordermanager.utils.file;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadTxtFileTest {

    @Test
    public void testExtractLinesFromEmptyFile() {
        final String content = "";
        final InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        final ReadFile readFile = new ReadTxtFile();

        final List<String> lines = readFile.extractLinesFrom(inputStream);

        assertTrue(lines.isEmpty());
    }

    @Test
    public void testExtractLinesFromSingleLineFile() {
        final String content = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        final ReadFile readFile = new ReadTxtFile();

        final List<String> lines = readFile.extractLinesFrom(inputStream);

        assertEquals(1, lines.size());
        assertEquals(content, lines.get(0));
    }

    @Test
    public void testExtractLinesFromMultiLineFile() {
        final String content = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308" +
                "\n0000000075                                  Bobbie Batz00000007980000000002     1578.5720211116" +
                "\n0000000049                               Ken Wintheiser00000005230000000003      586.7420210903";
        final InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        final ReadFile readFile = new ReadTxtFile();

        final List<String> lines = readFile.extractLinesFrom(inputStream);

        assertEquals(3, lines.size());
        assertEquals("0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308", lines.get(0));
        assertEquals("0000000075                                  Bobbie Batz00000007980000000002     1578.5720211116", lines.get(1));
        assertEquals("0000000049                               Ken Wintheiser00000005230000000003      586.7420210903", lines.get(2));
    }

    @Test
    public void testExtractLinesFromIOException() {
        final InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        final ReadFile readFile = new ReadTxtFile();

        final List<String> lines = readFile.extractLinesFrom(inputStream);

        assertTrue(lines.isEmpty());
    }

}