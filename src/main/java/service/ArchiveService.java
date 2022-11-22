package service;


import storage.Storage;

import storage.StorageType;
import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.util.*;

@Log4j2
public class ArchiveService {

    private final TextBasedStorageFactory textBasedStorageFactory;

    private Storage storage;

    public ArchiveService(StorageType storageType) {
        textBasedStorageFactory = new TextBasedStorageFactory(storageType);
        this.storage = textBasedStorageFactory.getStorage();
    }

    public void addMovie(Movie movie) {
        storage.connect();
        storage.addMovie(movie);
        storage.disconnect();
        log.info("Created movie with id: " + movie.getId());
    }

    public void deleteMovie(int movieId) {
        storage.connect();
        storage.deleteMovie(movieId);
        storage.disconnect();
        log.info("Deleted movie with id: " + movieId);
    }

    public void updateMovie(Movie movie) {
        storage.connect();
        storage.updateMovie(movie);
        storage.disconnect();
        log.info("Updated movie with id: " + movie.getId());
    }

    public List<Movie> getMovies() {
        storage.connect();
        List<Movie> movies = storage.readMovies();
        storage.disconnect();
        return movies;
    }

    public void setStorageType(StorageType storageType) {
        textBasedStorageFactory.setStorageType(storageType);
        this.storage = textBasedStorageFactory.getStorage();
    }

}
