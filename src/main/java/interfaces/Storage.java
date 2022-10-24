package interfaces;

import model.Movie;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Storage {

    void connect();

    void disconnect();

    void addMovie(Movie movie);
    List<Movie> readMovies();
    void updateMovie(Movie movie);

    void deleteMovie(int movieId);

}
