package Cs2263.UI.Commands;

import Cs2263.UI.UICommand;

public class PrintHelloCommand extends UICommand {


    @Override
    public void execute() {
        System.out.println("Hello World!");
    }
}
