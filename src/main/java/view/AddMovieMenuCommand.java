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
		Scanner scanner = new Scanner(System.in);

		System.out.print("Titolo:");
		String title = scanner.nextLine().trim();

		System.out.print("Autore:");
		String author = scanner.nextLine().trim();

		System.out.print("Anno:");
		int year = scanner.nextInt();
		scanner.nextLine();

		try {
			controller.addMovie(title, author, year);
			System.out.println("Film correttamente aggiunto");
		} catch (Exception e) {
			System.err.println("film non  aggiunto");
		}
	}
}
