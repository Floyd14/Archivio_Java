package interfaces;


import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.StandardOpenOption.APPEND;

@Log4j2
public class MemoryStorage implements Storage {

    private Map<Integer, Movie> movies;

    public MemoryStorage() {
        movies = new HashMap<>();
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
        movies.put(movie.getId(), movie);
    }

    @Override
    public List<Movie> readMovies(){
        return new ArrayList<>(movies.values());
    }

    @Override
    public void updateMovie(Movie movie) {
        //implement
    }

    @Override
    public void deleteMovie(int movieId) {
        //implement
    }

}
