package storage;

import model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryStorageTest {

    private IdentifierGeneratingStorage storage;

    @BeforeEach
    void createMovies() {
        storage = new MemoryStorage();
        storage.addMovie(new Movie("titolo1", "autore1", 2001));
        storage.addMovie(new Movie("titolo2", "autore2", 2002));
    }

    @Test
    void addMovie() {
        List<Movie> movies = storage.readMovies();
        assertEquals(2, movies.size());
        storage.addMovie(new Movie("titolo3", "autore3", 2003));

        movies = storage.readMovies();
        assertEquals(3, movies.size());

        Movie firstMovie = movies.get(movies.size()-1);
        assertEquals(2, firstMovie.getId());
        assertEquals(2003, firstMovie.getYear());
        assertEquals("autore3", firstMovie.getAuthor());
        assertEquals("titolo3", firstMovie.getTitle());
    }

    @Test
    void deleteMovie() {

        List<Movie> movies = storage.readMovies();
        assertEquals(2, movies.size());

        storage.deleteMovie(0);
        movies = storage.readMovies();

        assertEquals(1, movies.size());
    }

    @Test
    void getNextId() {
        assertEquals(2, storage.getNextId());
        storage.deleteMovie(1);
        assertEquals(1, storage.getNextId());
    }

    @Test
    void getStorageType() {
        assertEquals(StorageType.MEM, storage.getStorageType());
    }

    @Test
    void updateMovie() {
        List<Movie> movies = storage.readMovies();

        Movie firstMovie = movies.get(0);
        assertEquals(0, firstMovie.getId());
        assertEquals(2001, firstMovie.getYear());
        assertEquals("autore1", firstMovie.getAuthor());
        assertEquals("titolo1", firstMovie.getTitle());

        firstMovie.setTitle("titolo2");
        firstMovie.setAuthor("autore2");
        firstMovie.setYear(2002);

        storage.updateMovie(firstMovie);
        movies = storage.readMovies();

        Movie updatedMovie = movies.get(0);
        assertEquals(0, updatedMovie.getId());
        assertEquals(2002, updatedMovie.getYear());
        assertEquals("autore2", updatedMovie.getAuthor());
        assertEquals("titolo2", updatedMovie.getTitle());
    }

    @Test
    void readMovies() {

        List<Movie> movies = storage.readMovies();
        assertEquals(2, movies.size());

        Movie firstMovie = movies.get(0);
        assertEquals(0, firstMovie.getId());
        assertEquals(2001, firstMovie.getYear());
        assertEquals("autore1", firstMovie.getAuthor());
        assertEquals("titolo1", firstMovie.getTitle());

        Movie secondMovie = movies.get(1);
        assertEquals(1, secondMovie.getId());
        assertEquals(2002, secondMovie.getYear());
        assertEquals("autore2", secondMovie.getAuthor());
        assertEquals("titolo2", secondMovie.getTitle());
    }


}