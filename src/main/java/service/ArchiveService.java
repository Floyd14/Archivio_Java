package service;

import interfaces.MemoryStorage;
import interfaces.MySQLStorage;
import interfaces.Storage;
import interfaces.TxtStorage;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.util.*;

@Log4j2
public class ArchiveService {

    private final Storage storage;


    public ArchiveService() {

        //this.storage = new MemoryStorage();
        this.storage = new TxtStorage();
        //this.storage = new MySQLStorage();
    }

    public void addMovie(Movie movie){
        storage.connect();
        storage.addMovie(movie);
        storage.disconnect();

    }
    public void deleteMovie(int movieId){
        storage.connect();
        storage.deleteMovie(movieId);
        storage.disconnect();
    }

    public void updateMovie(Movie movie) {
        storage.connect();
        storage.updateMovie(movie);
        storage.disconnect();
    }

    public List<Movie> getMovies(){
        storage.connect();
        List<Movie> movies = storage.readMovies();
        storage.disconnect();
        return movies;
    }
}
