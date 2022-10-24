package view;

import controller.Controller;

public class ListMoviesMenuCommand extends MenuCommand {

    public ListMoviesMenuCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.listMovies();
    }


}