import java.util.ArrayList;
import java.util.List;

public class Archivio {

    private List<Movie> movies = new ArrayList<Movie>();

    public void addMovie(Movie movie){
        movies.add(movie);
    }
    public void deleteMovie(Movie movie){
        movies.remove(movie);
    }
    public void listMovie(){
        movies.forEach(System.out::println);
    }

}
