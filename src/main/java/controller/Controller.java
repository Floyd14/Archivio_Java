package controller;

import lombok.extern.log4j.Log4j2;
import model.Movie;
import service.ArchiveService;

import java.util.List;

@Log4j2
public class Controller {

	private final ArchiveService archiveService;

	public Controller() {

		this.archiveService = new ArchiveService();
	}

	public void addMovie(String title, String author, int year) {
		Movie movie = new Movie(title, author, year);
		archiveService.addMovie(movie);
	}

	public void deleteMovie(int movieId) {

		archiveService.deleteMovie(movieId);
	}

	public void updateMovie(int movieId, String title, String author, int year) {
		List<Movie> movies = getMovies();
		Movie movie = movies.get(movieId);
		movie.setTitle(title);
		movie.setAuthor(author);
		movie.setYear(year);
		archiveService.updateMovie(movie);
	}

	public List<Movie> getMovies() {

		return archiveService.getMovies();
	}
}
