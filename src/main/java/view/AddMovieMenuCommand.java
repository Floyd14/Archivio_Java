package view;

import controller.Controller;
import model.Movie;

import java.sql.SQLOutput;
import java.util.Scanner;

public class AddMovieMenuCommand extends MenuCommand {


	public AddMovieMenuCommand(Controller controller) {
		super(controller);
	}

	@Override
	public void execute() {
		System.out.println("LEGGO E AGGIUNGO FILM");

		Scanner scanner = new Scanner(System.in);

		System.out.print("Titolo:");
		String titolo = scanner.nextLine().trim();

		System.out.print("Autore:");
		String autore = scanner.nextLine().trim();

		System.out.print("Anno:");
		int anno = scanner.nextInt();

		try {
			controller.addMovie(titolo, autore, anno);
			System.out.println("Film correttamente aggiunto");
		} catch (Exception e) {
			System.err.println("L'anno deve essere un numero intero");
		}
	}
}
