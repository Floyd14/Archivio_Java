package Interfaces;

import java.io.IOException;

public interface Interface {

    boolean connected = false;
    String connectionMode = null;

    boolean connect();
    boolean disconnect() throws IOException;

}
