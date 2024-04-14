package io.github.mayc0n23.ordermanager.utils.file;

import java.io.InputStream;
import java.util.List;

public interface ReadFile {

    List<String> extractLinesFrom(InputStream file);

}