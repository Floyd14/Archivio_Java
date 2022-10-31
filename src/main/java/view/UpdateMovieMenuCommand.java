package view;

import controller.Controller;
import lombok.extern.log4j.Log4j2;


import java.util.Scanner;
@Log4j2
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

        System.out.print("ID to modify:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Title:");
        String title = scanner.nextLine().trim();

        System.out.print("Author:");
        String author = scanner.nextLine().trim();

        System.out.print("Year:");
        int year = scanner.nextInt();

        try {
            controller.updateMovie(id, title, author, year);
            System.out.printf("Film {%s} modified\n", id);
        } catch (Exception e) {
            System.err.println("film not modified");
        }
    }
}
