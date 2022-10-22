package view;

import controller.Controller;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class AddMovieMenuCommand extends MenuCommand {


    public void execute() {

    }

    @Override
    public void execute(@NotNull Controller ctr) {
        System.out.println("LEGGO E AGGIUNGO FILM");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Titolo:");
        String titolo = scanner.nextLine().trim();

        System.out.print("Autore:");
        String autore = scanner.nextLine().trim();

        System.out.print("Anno:");
        try {
            int anno = Integer.parseInt(scanner.nextLine().trim());
            ctr.addMovie(titolo, autore, anno);
        } catch (Exception e){
            System.err.println("L'anno deve essere un numero intero");
        }
    }
}
