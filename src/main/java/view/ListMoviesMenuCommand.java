package view;

import controller.Controller;

public class ListMoviesMenuCommand extends MenuCommand {

    @Override
    public void execute(Controller ctr) {

        ctr.listMovies();
    }
}