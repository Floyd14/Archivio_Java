package view;

import controller.Controller;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class MenuCommand {

    protected Controller controller;

    public MenuCommand(Controller controller) {

        this.controller = controller;
    }

    public abstract void execute();

}

