package view;

import controller.Controller;
import util.Constant;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {

    private final HashMap<String, MenuCommand> commandMap;

    public Menu() {
        Controller controller = new Controller();
        commandMap = new HashMap<>();
        commandMap.put("a", new AddMovieMenuCommand(controller));
        commandMap.put("l", new ListMoviesMenuCommand(controller));
        commandMap.put("d", new DeleteMovieMenuCommand(controller));
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("q")) {
            System.out.println(Constant.MENU_PROMPT);
            choice = scanner.nextLine().trim();
            if (!choice.equalsIgnoreCase("q"))
                commandMap.get(choice).execute();
        }
    }
}
