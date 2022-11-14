package service;

import interfaces.*;

public class TextBasedStorageFactory implements StorageFactory {

	private StorageType storageType;

	public TextBasedStorageFactory(StorageType storageType) {
		this.storageType = storageType;
	}

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public Storage getStorage() {

		return switch (storageType) {
			case MEM -> new MemoryStorage();
			case TXT -> new TxtStorage();
			default -> new MySQLStorage();
		};
	}
}
