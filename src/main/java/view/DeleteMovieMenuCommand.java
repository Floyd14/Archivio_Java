package view;

import controller.Controller;
import model.Movie;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

public class DeleteMovieMenuCommand extends MenuCommand {

    public DeleteMovieMenuCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {

        // Corretto?
        ListMoviesMenuCommand listMoviesMenuCommand = new ListMoviesMenuCommand(controller);
        listMoviesMenuCommand.execute();

        System.out.println("ID da eliminare:");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine().trim());
        controller.deleteMovie(id);
        System.out.println("Film cancellato correttamente");
    }

//    //TODO metodo private per mostrare gli id dei movie
//    private void listMoviesId() {
//        for (Movie movie : controller.getMovies()) {
//            System.out.println(movie.getId());
//        }
//    }

}