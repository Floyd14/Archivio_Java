package view;

import controller.Controller;
import model.Movie;

import java.sql.SQLOutput;
import java.util.List;

public class ListMoviesMenuCommand extends MenuCommand {

    public ListMoviesMenuCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        List<Movie> movies = controller.getMovies();
        for (Movie movie : movies){
            System.out.println("Movie ID: " + movie.getId());
            System.out.println("Movie Title: " + movie.getTitolo());
            System.out.println("Movie Author: " + movie.getAutore());
            System.out.println("Movie Year: " + movie.getAnno());
        }
    }


}