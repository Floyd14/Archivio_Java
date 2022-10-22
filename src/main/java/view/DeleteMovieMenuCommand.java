package view;

import controller.Controller;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class DeleteMovieMenuCommand extends MenuCommand {

    @Override
    public void execute(@NotNull Controller ctr) {
        ctr.listMovies();
        System.out.print("ID da eliminare:");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine().trim());
        ctr.deleteMovie(id);
    }
}