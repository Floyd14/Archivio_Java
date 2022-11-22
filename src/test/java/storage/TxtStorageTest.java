package storage;

import lombok.extern.log4j.Log4j2;
import model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class TxtStorageTest {

    private File file;

    @AfterEach
    void deleteTmpFile(){
        try {
            Files.deleteIfExists(file.toPath());
        } catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }

    @BeforeEach
    void setupTxtFile() {
        try {
            file = File.createTempFile("test", ".txt");
            String movieString = "0,Via col vento,Peppino,2000\n1,Interstellar,Cristopher Nolan,2014\n";

            BufferedWriter bufferedWriter = Files.newBufferedWriter(
                    file.toPath(),
                    defaultCharset());
            bufferedWriter.write(movieString + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    void addMovie() {
        Storage storage = new TxtStorage(file);
        List<Movie> movies = storage.readMovies();

        assertEquals(2, movies.size());

        Movie movie = new Movie(3, "Cloud Atlas", "Wachowski", 2013);
        storage.addMovie(movie);
        movies = storage.readMovies();

        assertEquals(3, movies.size());
        assertTrue(movies.contains(movie));
    }

    @Test
    void readMovies() {
        Storage storage = new TxtStorage(file);
        List<Movie> movies = storage.readMovies();
        assertEquals(2, movies.size());

        Movie firstMovie = movies.get(0);
        assertEquals(0, firstMovie.getId());
        assertEquals(2000, firstMovie.getYear());
        assertEquals("Peppino", firstMovie.getAuthor());
        assertEquals("Via col vento", firstMovie.getTitle());

        Movie secondMovie = movies.get(1);
        assertEquals(1, secondMovie.getId());
        assertEquals(2014, secondMovie.getYear());
        assertEquals("Cristopher Nolan", secondMovie.getAuthor());
        assertEquals("Interstellar", secondMovie.getTitle());
    }

    @Test
    void updateMovie() {
        Storage storage = new TxtStorage(file);
        List<Movie> movies = storage.readMovies();
        assertEquals(2, movies.size());

        Movie firstMovie = movies.get(0);

        assertEquals(2000, firstMovie.getYear());

        firstMovie.setYear(2001);
        storage.updateMovie(firstMovie);
        movies = storage.readMovies();
        assertEquals(2, movies.size());
        firstMovie = movies.get(0);

        assertEquals(2001, firstMovie.getYear());
    }

    @Test
    void deleteMovie() {

        Storage storage = new TxtStorage(file);
        List<Movie> movies = storage.readMovies();

        assertEquals(2, movies.size());

        storage.deleteMovie(0);
        movies = storage.readMovies();

        assertEquals(1, movies.size());
        assertEquals(1, movies.get(0).getId());
    }

    @Test
    void getStorageType() {
        Storage storage = new TxtStorage(file);
        assertEquals(StorageType.TXT, storage.getStorageType());
    }
}