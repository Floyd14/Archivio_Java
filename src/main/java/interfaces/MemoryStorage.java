package interfaces;


import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class MemoryStorage implements Storage {

    private Map<Integer, Movie> moviesStorage;
   // private int counter;

    public MemoryStorage() {
        moviesStorage = new HashMap<>();
       // counter = 0;
    }

    @Override
    public void connect() {
        // non serve per la memory storage
    }

    @Override
    public void disconnect() {
        // non serve per la memory storage
    }

    @Override
    public void addMovie(Movie movie) {

        movie.setId(getNextId());
        moviesStorage.put(movie.getId(), movie);
    }

    @Override
    public void deleteMovie(int movieId) {

        moviesStorage.remove(movieId);
        refreshMemoryStorage();
    }

    @Override
    public int getNextId() {

        return moviesStorage.size();
    }

    @Override
    public void updateMovie(Movie movie) {

        moviesStorage.put(movie.getId(), movie);
    }

    @Override
    public List<Movie> readMovies() {

        return new ArrayList<>(moviesStorage.values());
    }

    private void refreshMemoryStorage() {
        Map<Integer, Movie> updatedMoviesStorage = new HashMap<>();
        int i = 0;
        for (Movie movie : moviesStorage.values()) {
            movie.setId(i);
            updatedMoviesStorage.put(i, movie);
            i++;
        }
        moviesStorage = updatedMoviesStorage;
    }

}
