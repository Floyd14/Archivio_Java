package view;

import controller.Controller;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;
@Log4j2
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
}