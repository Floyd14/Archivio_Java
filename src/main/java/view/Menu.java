package view;

import controller.Controller;
import util.Constant;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {

    private final HashMap<String, MenuCommand> commandMap;

    public Menu() {
        commandMap = new HashMap<>();
        commandMap.put("a", new AddMovieMenuCommand());
        commandMap.put("l", new ListMoviesMenuCommand());
        commandMap.put("d", new DeleteMovieMenuCommand());
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        Controller c = new Controller();
        String choice = "";
        while (!choice.equalsIgnoreCase("q")) {
            System.out.println(Constant.MENU_PROMPT);
            choice = scanner.nextLine().trim();
            if (!choice.equalsIgnoreCase("q"))
                commandMap.get(choice).execute(c);
        }
    }
}
