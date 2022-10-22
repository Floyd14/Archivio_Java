package service;

import interfaces.TxtInterface;
import model.Movie;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArchivioService {

    private final List<Movie> movies;
    private final TxtInterface txt;

    public ArchivioService() {

        this.movies = new ArrayList<Movie>();
        this.txt = new TxtInterface(Path.of("file.txt"));
    }

    public Map<String,String> movieToMap(Movie movie) {

        Map<String, String> movieMap = new LinkedHashMap<String, String>();
        movieMap.put("id", Integer.toString(movie.getId()));
        movieMap.put("Titolo",movie.getTitolo());
        movieMap.put("Autore", movie.getAutore() );
        movieMap.put("Anno", Integer.toString(movie.getAnno()));
        return movieMap;
    }
    public void addMovie(Movie movie){

        movies.add(movie);
        txt.writeLine();
    }
    public void deleteMovie(int movieId){

        movies.remove(movieId);
    }
    public void listMovie(){

        movies.forEach(System.out::println);
    }

}
