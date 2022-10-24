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

	public void addMovie(String title, String author, int anno) {
		Movie movie = new Movie(title, author, anno);
		archiveService.addMovie(movie);
	}

	public void deleteMovie(int movieId) {

		archiveService.deleteMovie(movieId);
	}

	public void updateMovie(int movieId, Movie movie) {

		archiveService.updateMovie(movie);
	}

	public List<Movie> getMovies() {

		return archiveService.getMovies();
	}
}
