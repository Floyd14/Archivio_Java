package view;

import controller.Controller;
import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.util.List;
@Log4j2
public class ListMoviesMenuCommand extends MenuCommand {

    public ListMoviesMenuCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        List<Movie> movies = controller.getMovies();
        try {
            for (Movie movie : movies) {
                System.out.println("Movie ID: " + movie.getId());
                System.out.println("Movie Title: " + movie.getTitle());
                System.out.println("Movie Author: " + movie.getAuthor());
                System.out.println("Movie Year: " + movie.getYear());
                System.out.println("------------------------------------");
            }
        }
        catch (NullPointerException e){
            System.out.println("There was an error while reading movies.");
            System.out.println("------------------------------------");
        }
    }


}