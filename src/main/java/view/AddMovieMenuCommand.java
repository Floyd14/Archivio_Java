package view;

import controller.Controller;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;
@Log4j2
public class AddMovieMenuCommand extends MenuCommand {


	public AddMovieMenuCommand(Controller controller) {
		super(controller);
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Title:");
		String title = scanner.nextLine().trim();

		System.out.print("Author:");
		String author = scanner.nextLine().trim();

		System.out.print("Anno:");
		int year = scanner.nextInt();
		scanner.nextLine();

		try {
			controller.addMovie(title, author, year);
			System.out.println("Film added");
		} catch (Exception e) {
			System.err.println("film nont added");
		}
	}
}
