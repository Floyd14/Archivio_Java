package service;


import interfaces.Storage;

import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.util.*;

@Log4j2
public class ArchiveService {

    private final StorageFactory storageFactory;

    private Storage storage;

    public ArchiveService() {
        storageFactory = new StorageFactory();
        this.storage = storageFactory.storage();
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

    public void setStorageType(String storageType) {
        this.storage = storageFactory.storage(storageType);
    }

}
