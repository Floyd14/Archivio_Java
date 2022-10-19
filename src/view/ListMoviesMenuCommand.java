package src.view;

import src.controller.Controller;

public class ListMoviesMenuCommand extends MenuCommand {

    @Override
    public void execute() {
        System.out.println("MOSTRO I FILM");
    }


    @Override
    public void execute(Controller ctr) {
        ctr.listMovies();
    }
}