import Interfaces.TxtInterfcace;

public class Controller {

    private final Archivio archivio = new Archivio();
    public void addMovie(String titolo, String autore, int anno){
        Movie movie = new Movie(titolo, autore, anno);
        //System.out.println(movie);
        //TxtInterfcace txtInterfcace = new TxtInterfcace();
        archivio.addMovie(movie);
    }
    public void deleteMovie(Movie movie){
        archivio.deleteMovie(movie);
    }
    public void listMovies(){
        archivio.listMovie();
    }
}
