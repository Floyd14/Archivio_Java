package service;

import interfaces.MemoryStorage;
import interfaces.MySQLStorage;
import interfaces.Storage;
import interfaces.TxtStorage;

public class StorageFactory {

    public Storage storage() {
        return new MySQLStorage();
    }

    public Storage storage(String storageType) {

        return switch (storageType) {
            case "MEM" -> new MemoryStorage();
            case "TXT" -> new TxtStorage();
            default -> new MySQLStorage();
        };
    }
}
