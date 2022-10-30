package interfaces;

import model.Movie;

import java.sql.SQLException;
import java.util.List;

public interface Storage {

    void connect();

    void disconnect();

    void addMovie(Movie movie);
    List<Movie> readMovies();
    void updateMovie(Movie movie);

    void deleteMovie(int movieId);
    int getNextId(); //che id dare ad ogni nuova movie? Questo metodo risponde alla domanda

}
