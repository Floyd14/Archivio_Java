package view;

import controller.Controller;

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
		try {
			int anno = scanner.nextInt();
			controller.addMovie(titolo, autore, anno);
		} catch (Exception e) {
			System.err.println("L'anno deve essere un numero intero");
		}
	}
}
