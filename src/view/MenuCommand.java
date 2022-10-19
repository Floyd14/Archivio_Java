package src.view;

import src.controller.Controller;

public abstract class MenuCommand {

    public abstract void execute();

    public abstract void execute(Controller ctr);

}

