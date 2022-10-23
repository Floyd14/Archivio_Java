package service;

import interfaces.TxtInterface;
import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ArchiveService {
//TODO sincronizzare gli ID con il file
    private final List<Movie> movies;
    private final TxtInterface txt;

    public ArchiveService() {

        this.movies = new ArrayList<>();
        this.txt = new TxtInterface(Path.of("file.txt"));
    }

    public Map<String,String> movieToMap(Movie movie) {

        Map<String, String> movieMap = new LinkedHashMap<>();
        movieMap.put("id", Integer.toString(movie.getId()));
        movieMap.put("Title", movie.getTitolo());
        movieMap.put("Author", movie.getAutore());
        movieMap.put("Anno", Integer.toString(movie.getAnno()));
        return movieMap;
    }

    public void addMovie(Movie movie){

        // in memory:
        movies.add(movie);

        // on Txt:
        txt.writeLine(movieToMap(movie));
    }
    public void deleteMovie(int movieId){

        // in memory:
        movies.remove(movieId);

        // on Txt:

    }
    public void listMovie(){

        System.out.print("In Memory list:");
        movies.forEach(System.out::println);

        System.out.print("In Txt list:");
        txt.readAll();
    }

}
