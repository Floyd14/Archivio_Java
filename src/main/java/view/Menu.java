package view;

import controller.Controller;
import util.Constant;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.in;

public class Menu {

    private final HashMap<String, MenuCommand> commandMap;

    public Menu() {
        Controller controller = new Controller();
        commandMap = new HashMap<>();
        commandMap.put("a", new AddMovieMenuCommand(controller));
        commandMap.put("l", new ListMoviesMenuCommand(controller));
        commandMap.put("d", new DeleteMovieMenuCommand(controller));
        commandMap.put("u", new UpdateMovieMenuCommand(controller));
    }

    public void showMenu() {
        Scanner scanner = new Scanner(in);
        String choice = "";
        while (!choice.equalsIgnoreCase("q")) {
            System.out.println(Constant.MENU_PROMPT);
            choice = scanner.nextLine().trim();
            if (commandMap.containsKey(choice))
                commandMap.get(choice).execute();
            else System.out.println("Retry");
        }
    }
}
