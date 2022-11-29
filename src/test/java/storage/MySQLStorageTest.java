package storage;

import lombok.extern.log4j.Log4j2;
import model.Movie;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
class MySQLStorageTest {

	private Storage storage;


	@BeforeEach
	public void connect(){
		// initialise database (truncate table and populate with query)
		storage = new MySQLStorage("movies_test");
		storage.connect();
	}


	@AfterEach
	public void disconnect(){
		storage.disconnect();
	}

	@Test
	void addMovie() {
		storage.addMovie(new Movie(0, "titolo1",  "autore1",2001));
		// check that readmovie returns 3 or use another query (select count maybe)
	}

	@Test
	void readMovies() {
		// read only

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