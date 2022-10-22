package interfaces;

import java.nio.file.Path;

public interface Interface {

    void connect(Path filePath);

    void disconnect();
    void writeLine();
}
