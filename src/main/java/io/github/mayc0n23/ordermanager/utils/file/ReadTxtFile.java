package io.github.mayc0n23.ordermanager.utils.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTxtFile implements ReadFile {

    @Override
    public List<String> extractLinesFrom(InputStream file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ignored) {
            /* Ignorar as linhas que não foram lidas e seguir com o processamento */
        }
        return lines;
    }

}