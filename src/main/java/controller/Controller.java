package controller;

import service.ArchivioService;
import model.Movie;

public class Controller {

    private final ArchivioService archivioService;

    public Controller() {
        this.archivioService = new ArchivioService();
    }

    public void addMovie(String titolo, String autore, int anno){

        Movie movie = new Movie(titolo, autore, anno);
        //System.out.println(movie);
        //TxtInterfcace txtInterfcace = new TxtInterfcace();
        archivioService.addMovie(movie);
    }
    public void deleteMovie(int movieId){

        archivioService.deleteMovie(movieId);
    }

    public void listMovies(){

        archivioService.listMovie();
    }
}
