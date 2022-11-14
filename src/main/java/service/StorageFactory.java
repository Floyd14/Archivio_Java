package service;

import interfaces.MemoryStorage;
import interfaces.MySQLStorage;
import interfaces.Storage;
import interfaces.TxtStorage;

public interface StorageFactory {
	public Storage getStorage();

}
