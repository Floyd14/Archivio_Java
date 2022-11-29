package storage;

public interface IdentifierGeneratingStorage extends Storage{

	int getNextId();
}
