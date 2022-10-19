package src.service;

import src.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ArchivioService { //è un servizio

    private final List<Movie> movies = new ArrayList<Movie>(); //

    public void addMovie(Movie movie){
        movies.add(movie);
    }
    public void deleteMovie(int movieId){
        movies.remove(movieId);
    }
    public void listMovie(){
        movies.forEach(System.out::println);
    } //riferimeto al metodo meglio for ...

}
