package interfaces;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Interface {

    void connect(Path filePath);

    void disconnect();

    void writeLine(Map movieMap);

    List<String> readAll();
}
