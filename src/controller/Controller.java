package src.controller;

import src.service.ArchivioService;
import src.model.Movie;

public class Controller {

    private final ArchivioService archivio = new ArchivioService(); //inizializzo sempre nel costruttoore
    public void addMovie(String titolo, String autore, int anno){
        Movie movie = new Movie(titolo, autore, anno);
        //System.out.println(movie);
        //TxtInterfcace txtInterfcace = new TxtInterfcace();
        archivio.addMovie(movie);
    }
    public void deleteMovie(int movieId){
        archivio.deleteMovie(movieId);
    }

    public void listMovies(){
        archivio.listMovie();
    }
}
