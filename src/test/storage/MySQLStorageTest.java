package storage;

import lombok.extern.log4j.Log4j2;
import model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
class MySQLStorageTest {

    private Movie mov1;
    private Movie mov2;

    private Connection connect;
    private final String table = "movies_test";
    private Storage storage;

    @AfterEach
    void disconnect() {
        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        mov1 = null;
        mov2 = null;
    }

    @BeforeEach
    void connect() {
        try {
            if (connect == null || connect.isClosed())
                connect = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test",
                        "root",
                        "Ytrewq1!"
                );
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        mov1 = new Movie("titolo1", "autore1", 2001);
        mov2 = new Movie("titolo2", "autore2", 2002);
        storage = new MySQLStorage(table);
    }

    @Test
    void addMovie() {
        Storage storage = new MySQLStorage(table);

    }

    @Test
    void readMovies() {

        storage.addMovie(mov1);
        storage.addMovie(mov2);

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

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }

    @Test
    void getNextId() {
    }

    @Test
    void getStorageType() {
        Storage storage = new MySQLStorage();
        assertEquals(StorageType.SQL, storage.getStorageType());
    }
}