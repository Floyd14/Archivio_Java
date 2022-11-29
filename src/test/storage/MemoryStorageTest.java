package storage;

import model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryStorageTest {

    private Movie mov1;
    private Movie mov2;

    @BeforeEach
    void createMoovies() {
        mov1 = new Movie("titolo1", "autore1", 2001);
        mov2 = new Movie("titolo2", "autore2", 2002);
    }

    @AfterEach
    void deleteMovies() {
        mov1 = null;
        mov2 = null;
    }

    @Test
    void addMovie() {
        Storage storage = new MemoryStorage();
        storage.addMovie(mov1);

        List<Movie> movies = storage.readMovies();
        assertEquals(1, movies.size());

        Movie firstMovie = movies.get(0);
        assertEquals(0, firstMovie.getId());
        assertEquals(2001, firstMovie.getYear());
        assertEquals("autore1", firstMovie.getAuthor());
        assertEquals("titolo1", firstMovie.getTitle());

        storage.addMovie(mov2);

        movies = storage.readMovies();
        assertEquals(2, movies.size());

        Movie secondMovie = movies.get(1);
        assertEquals(1, secondMovie.getId());
        assertEquals(2002, secondMovie.getYear());
        assertEquals("autore2", secondMovie.getAuthor());
        assertEquals("titolo2", secondMovie.getTitle());
    }


    @Test
    void deleteMovie() {
        Storage storage = new MemoryStorage();
        storage.addMovie(mov1);

        List<Movie> movies = storage.readMovies();
        assertEquals(1, movies.size());

        storage.deleteMovie(0);
        movies = storage.readMovies();

        assertEquals(0, movies.size());

    }

    @Test
    void getNextId() {
        Storage storage = new MemoryStorage();
        storage.addMovie(mov1);

        assertEquals(1, storage.getNextId());

        storage.addMovie(mov2);
        assertEquals(2, storage.getNextId());

        storage.deleteMovie(0);
        assertEquals(1, storage.getNextId());
    }

    @Test
    void getStorageType() {
        Storage storage = new MemoryStorage();
        assertEquals(StorageType.MEM, storage.getStorageType());
    }

    @Test
    void updateMovie() {
        Storage storage = new MemoryStorage();
        storage.addMovie(mov1);
        List<Movie> movies = storage.readMovies();

        Movie firstMovie = movies.get(0);
        assertEquals(0, firstMovie.getId());
        assertEquals(2001, firstMovie.getYear());
        assertEquals("autore1", firstMovie.getAuthor());
        assertEquals("titolo1", firstMovie.getTitle());

        mov1.setTitle("titolo2");
        mov1.setAuthor("autore2");
        mov1.setYear(2002);

        storage.updateMovie(mov1);
        movies = storage.readMovies();

        Movie updatedMovie = movies.get(0);
        assertEquals(0, updatedMovie.getId());
        assertEquals(2002, updatedMovie.getYear());
        assertEquals("autore2", updatedMovie.getAuthor());
        assertEquals("titolo2", updatedMovie.getTitle());

    }

    @Test
    void readMovies() {
        Storage storage = new MemoryStorage();
        storage.addMovie(mov1);

        List<Movie> movies = storage.readMovies();
        Movie firstMovie = movies.get(0);
        assertEquals(0, firstMovie.getId());
        assertEquals(2001, firstMovie.getYear());
        assertEquals("autore1", firstMovie.getAuthor());
        assertEquals("titolo1", firstMovie.getTitle());

        storage.addMovie(mov2);

        movies = storage.readMovies();
        assertEquals(2, movies.size());

        Movie secondMovie = movies.get(1);
        assertEquals(1, secondMovie.getId());
        assertEquals(2002, secondMovie.getYear());
        assertEquals("autore2", secondMovie.getAuthor());
        assertEquals("titolo2", secondMovie.getTitle());
    }
}