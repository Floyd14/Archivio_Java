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

        System.out.println("ID da modificare:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        System.out.println("Titolo:");
        String title = scanner.nextLine().trim();

        System.out.println("Autore:");
        String author = scanner.nextLine().trim();

        System.out.println("Anno:");
        int year = scanner.nextInt();

        Movie movie = new Movie(title, author, year);

        try {
            controller.updateMovie(id, movie);
            System.out.printf("Film {%s} correttamente modificato\n", id);
        } catch (Exception e) {
            System.err.println("L'anno deve essere un numero intero");
        }
    }
}
