package view;

import controller.Controller;
import model.Movie;

import java.util.Scanner;

public class UpdateMovieMenuCommand extends MenuCommand {
    public UpdateMovieMenuCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {

        // Corretto?
        ListMoviesMenuCommand listMoviesMenuCommand = new ListMoviesMenuCommand(controller);
        listMoviesMenuCommand.execute();

        Scanner scanner = new Scanner(System.in);

        System.out.print("ID da modificare:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Titolo:");
        String title = scanner.nextLine().trim();

        System.out.print("Autore:");
        String author = scanner.nextLine().trim();

        System.out.print("Anno:");
        int year = scanner.nextInt();

        try {
            controller.updateMovie(id, title, author, year);
            System.out.printf("Film {%s} correttamente modificato\n", id);
        } catch (Exception e) {
            System.err.println("film non modificato");
        }
    }
}
