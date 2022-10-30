package interfaces;


import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class MemoryStorage implements Storage {

    private Map<Integer, Movie> movies;
    private int counter;
    public MemoryStorage() {
        movies = new HashMap<>();
        counter = 0;
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
    public void addMovie(Movie movie){
        movie.setId(getNextId());
        movies.put(movie.getId(), movie);
    }

    @Override
    public void deleteMovie(int movieId) {
        movies.remove(movieId);
    }

    @Override
    public int getNextId() {
        return counter++;
    }

    @Override
    public void updateMovie(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    @Override
    public List<Movie> readMovies(){
        return new ArrayList<>(movies.values());
    }

}
