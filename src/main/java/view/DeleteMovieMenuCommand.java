package view;

import controller.Controller;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class DeleteMovieMenuCommand extends MenuCommand {

    public DeleteMovieMenuCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.listMovies();
        System.out.println("ID da eliminare:");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine().trim());
        controller.deleteMovie(id);
    }
}