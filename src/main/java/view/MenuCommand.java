package view;

import controller.Controller;

public abstract class MenuCommand {

    protected Controller controller;

    public MenuCommand(Controller controller) {
        this.controller = controller;
    }

    public abstract void execute();

}

