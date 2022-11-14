package interfaces;

import model.Movie;


import java.util.List;

public interface Storage {

    void connect();

    void disconnect();

    void addMovie(Movie movie);

    List<Movie> readMovies();

    void updateMovie(Movie movie);

    void deleteMovie(int movieId);

    public int getNextId();

    public String getStorageType();

}
